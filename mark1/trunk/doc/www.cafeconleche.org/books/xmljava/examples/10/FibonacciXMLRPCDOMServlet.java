import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.math.BigInteger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class FibonacciXMLRPCDOMServlet extends HttpServlet 
 implements SingleThreadModel {

  // Fault codes   
  public final static int MALFORMED_REQUEST_DOCUMENT = 1;
  public final static int INVALID_REQUEST_DOCUMENT   = 2;
  public final static int INDEX_MISSING              = 3;
  public final static int NON_POSITIVE_INDEX         = 4;
  public final static int BAD_INTEGER_FORMAT         = 5;
  public final static int UNEXPECTED_PROBLEM         = 255;
   
  private DocumentBuilder   parser;
  private DOMImplementation impl;
  private Transformer       idTransform;
  
  // Load a parser, transformer, and implementation
  public void init() throws ServletException {  
  
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      this.parser = factory.newDocumentBuilder();
      this.impl   = parser.getDOMImplementation();
    }
    catch (Throwable t) { 
      // It's unusual to catch a generic Throwable instead of an
      // exception. Here I'm specifically worried about 
      // FactoryConfigurationErrors and
      // ParserConfigurationExceptions, both of which are real
      // possibilities in a servlet environment because of the
      // weird ways servlet containers arrange classpaths.
      throw new ServletException(
       "Could not locate a JAXP parser", t); 
    }
    
    try {
      TransformerFactory xformFactory 
       = TransformerFactory.newInstance();  
      this.idTransform = xformFactory.newTransformer();
    }
    catch (Throwable t) { 
      throw new ServletException(
       "Could not locate a JAXP transformer", t); 
    }
    
  }   
  
  // Respond to an XML-RPC request
  public void doPost(HttpServletRequest servletRequest,
   HttpServletResponse servletResponse)
   throws ServletException, IOException {
    
    servletResponse.setContentType("text/xml; charset=UTF-8");               
    PrintWriter out = servletResponse.getWriter();
    InputStream in  = servletRequest.getInputStream();

    Document request;
    Document response;
    try {
      request = parser.parse(in);

      NodeList ints = request.getElementsByTagName("int");
      if (ints.getLength() == 0) {
        // XML-RPC allows i4 as an alias for int.
        ints = request.getElementsByTagName("i4"); 
      }
      Node input = ints.item(0); // throws NullPointerException
      String generations = getFullText(input);
      int numberOfGenerations = Integer.parseInt(generations);
      BigInteger result = calculateFibonacci(numberOfGenerations);
      response = makeResponseDocument(result);
    }
    catch (SAXException e) {  
      response = makeFaultDocument(MALFORMED_REQUEST_DOCUMENT, e.getMessage());
    }
    catch (NullPointerException e) {  
      response = makeFaultDocument(INDEX_MISSING, e.getMessage());
    }
    catch (NumberFormatException e) {  
      response = makeFaultDocument(BAD_INTEGER_FORMAT, e.getMessage());
    }
    catch (IndexOutOfBoundsException e) {  
      response = makeFaultDocument(NON_POSITIVE_INDEX, e.getMessage());
    }
    catch (Exception e) {  
      response = makeFaultDocument(UNEXPECTED_PROBLEM, e.getMessage());
    }
    
    // Transform onto the OutputStream
    try {
      Source input = new DOMSource(response);
      Result output = new StreamResult(out);
      idTransform.transform(input, output);
      servletResponse.flushBuffer();
      out.flush(); 
      out.println();
    }
    catch (TransformerException e) {
      // If we get an exception at this point, it's too late to
      // switch over to an XML-RPC fault.
      throw new ServletException(e); 
    }
    
  }

  
  // Given a node which does not contain any Element children,
  // accumulate all its text content from both text nodes and
  // CDATA sections (but not comments or processing instructions 
  // and return it as a single string.
  private static String getFullText(Node node) {
    
    StringBuffer result = new StringBuffer();
    
    NodeList children = node.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      int type = child.getNodeType();
      if (type == Node.TEXT_NODE 
       || type == Node.CDATA_SECTION_NODE) {
        result.append(child.getNodeValue()); 
      }
      else if (type == Node.ENTITY_REFERENCE_NODE) {
        // The JAXP spec is unclear about whether or not it's
        // possible for entity reference nodes to appear in the
        // tree. Just in case, let's expand them recursively:
        result.append(getFullText(child));
        // Validity does require that if they do appear their 
        // replacement text is pure text, no elements.
      }
    }
    
    return result.toString();
    
  }
  
  // If performance is an issue, this could be pre-built in the
  // init() method and then cached. You'd just change one text 
  // node each time.  This would only work in a SingleThreadModel 
  // servlet.
  public Document makeResponseDocument(BigInteger result) {
    
    Document response 
     = impl.createDocument(null, "methodResponse", null);
     
    Element methodResponse = response.getDocumentElement();
    Element params         = response.createElement("params");
    Element param          = response.createElement("param");
    Element value          = response.createElement("value");
    Element doubleElement  = response.createElement("double");
    Text    text = response.createTextNode(result.toString()); 
 
    methodResponse.appendChild(params);
    params.appendChild(param);
    param.appendChild(value);
    value.appendChild(doubleElement);
    doubleElement.appendChild(text);

    return response;
   
  }
  
  public Document makeFaultDocument(int faultCode, String faultString) {
        
    Document faultDoc
     = impl.createDocument(null, "methodResponse", null);
     
    Element methodResponse = faultDoc.getDocumentElement();
    
    Element fault         = faultDoc.createElement("fault");
    Element value         = faultDoc.createElement("value");
    Element struct        = faultDoc.createElement("struct");
    Element memberCode    = faultDoc.createElement("member");
    Element nameCode      = faultDoc.createElement("name");
    Text    nameCodeText  = faultDoc.createTextNode("faultCode");
    Element valueCode     = faultDoc.createElement("value");
    Element intCode       = faultDoc.createElement("int");
    String  codeString    = String.valueOf(faultCode);
    Text    textCode      = faultDoc.createTextNode(codeString);
    Element doubleElement = faultDoc.createElement("double");
    Element memberString  = faultDoc.createElement("member");
    Element nameString    = faultDoc.createElement("name");
    Text    nameText    = faultDoc.createTextNode("faultString");
    Element valueString   = faultDoc.createElement("value");
    Element stringString  = faultDoc.createElement("string");
    Text    textString    = faultDoc.createTextNode(faultString);

    methodResponse.appendChild(fault);
    fault.appendChild(value);
    value.appendChild(struct);
    struct.appendChild(memberCode);
    struct.appendChild(memberString);
    memberCode.appendChild(nameCode);
    memberCode.appendChild(valueCode);
    memberString.appendChild(nameString);
    memberString.appendChild(valueString);
    nameCode.appendChild(nameCodeText);
    nameString.appendChild(nameText);
    valueCode.appendChild(intCode);
    valueString.appendChild(stringString);
    intCode.appendChild(textCode);
    stringString.appendChild(textString);
    
    return faultDoc;
       
  } 
  
  public static BigInteger calculateFibonacci(int generations) 
   throws IndexOutOfBoundsException {
    
    if (generations < 1) {
      throw new IndexOutOfBoundsException(
       "Fibonacci numbers are not defined for " + generations 
       + "or any other number less than one.");
    }
    BigInteger low  = BigInteger.ONE;
    BigInteger high = BigInteger.ONE;      
    for (int i = 2; i <= generations; i++) {
      BigInteger temp = high;
      high = high.add(low);
      low = temp;
    }
    return low;   
        
  }

}
