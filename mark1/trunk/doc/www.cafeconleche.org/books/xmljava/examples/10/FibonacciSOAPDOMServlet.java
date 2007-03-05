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


public class FibonacciSOAPDOMServlet extends HttpServlet 
  implements SingleThreadModel {

  // Fault codes   
  public final static String MALFORMED_REQUEST_DOCUMENT 
   = "MalformedRequest";
  public final static String INVALID_REQUEST_DOCUMENT 
   = "InvalidRequest";
  public final static String INDEX_MISSING 
   = "IndexMissing";
  public final static String NON_POSITIVE_INDEX 
   = "NonPositiveIndex";
  public final static String BAD_INTEGER_FORMAT
  = "BadIntegerFormat";
  public final static String UNEXPECTED_PROBLEM
  = "UnexpectedProblem";    
    
  private DocumentBuilder   parser;
  private DOMImplementation impl;
  private Transformer       idTransform;
  
  // Load a parser, transformer, and implementation
  public void init() throws ServletException {  
  
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      // Always turn on namespace awareness
      factory.setNamespaceAware(true);

      this.parser = factory.newDocumentBuilder();
      this.impl   = parser.getDOMImplementation();
      
    }
    catch (Throwable t) { 
      throw new ServletException(
       "Could not locate a JAXP parser", t); 
    }
    
    try {
      TransformerFactory xformFactory = TransformerFactory.newInstance();  
      this.idTransform = xformFactory.newTransformer();
    }
    catch (Throwable t) { 
      throw new ServletException(
       "Could not locate a JAXP transformer", t); 
    }
    
  } 
  
  public void doPost(HttpServletRequest servletRequest,
   HttpServletResponse servletResponse)
   throws ServletException, IOException {
    
    servletResponse.setContentType("text/xml; charset=UTF-8");               
    PrintWriter out = servletResponse.getWriter();
    InputStream in  = servletRequest.getInputStream();

    Document request;
    Document response;
    String generations ="here";
    try {
      request = parser.parse(in);
       
      NodeList ints = request.getElementsByTagNameNS(
       "http://namespaces.cafeconleche.org/xmljava/ch3/", 
       "calculateFibonacci");
      Node input = ints.item(0);
      generations = getFullText(input);
      int numberOfGenerations = Integer.parseInt(generations);
      BigInteger result = calculateFibonacci(numberOfGenerations);
      response = makeResponseDocument(result);
    }
    catch (SAXException e) {  
      response = makeFaultDocument(MALFORMED_REQUEST_DOCUMENT, 
       e.getMessage());
    }
    catch (NullPointerException e) {  
      response = makeFaultDocument(INDEX_MISSING, 
       e.getMessage());
    }
    catch (NumberFormatException e) {  
      response = makeFaultDocument(BAD_INTEGER_FORMAT, 
       generations + e.getMessage());
    }
    catch (IndexOutOfBoundsException e) {  
      response = makeFaultDocument(NON_POSITIVE_INDEX, 
       e.getMessage());
    }
    catch (Exception e) {  
      response = makeFaultDocument(UNEXPECTED_PROBLEM, 
       e.getMessage());
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
      // switch over to a SOAP fault
      throw new ServletException(e); 
    }
    
  }

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
    }

    return result.toString();
    
  }  

  // The details of the formats and namespace URIs are likely to
  // change when SOAP 1.2 is released.
  public Document makeResponseDocument(BigInteger result) {
    
    Document response 
     = impl.createDocument(
      "http://schemas.xmlsoap.org/soap/envelope/", 
      "SOAP-ENV:Envelope", null);
     
    Element envelope = response.getDocumentElement();
    Element body = response.createElementNS(
     "http://schemas.xmlsoap.org/soap/envelope/", 
     "SOAP-ENV:Body");
    envelope.appendChild(body);
 
    Element Fibonacci_Numbers = response.createElementNS( 
     "http://namespaces.cafeconleche.org/xmljava/ch3/",
     "Fibonacci_Numbers");
    body.appendChild(Fibonacci_Numbers);

    Element fibonacci = response.createElementNS(
     "http://namespaces.cafeconleche.org/xmljava/ch3/",
     "fibonacci");
    Fibonacci_Numbers.appendChild(fibonacci);

    Text text = response.createTextNode(result.toString()); 
    fibonacci.appendChild(text);

    return response;
   
  }
  
  public Document makeFaultDocument(String code, String message){
    
    Document faultDoc = impl.createDocument(
      "http://schemas.xmlsoap.org/soap/envelope/", 
      "SOAP-ENV:Envelope", null);

    Element envelope = faultDoc.getDocumentElement();

    Element body = faultDoc.createElementNS(
     "http://schemas.xmlsoap.org/soap/envelope/", 
     "SOAP-ENV:Body");
    envelope.appendChild(body);
 
    Element fault = faultDoc.createElementNS(
     "http://schemas.xmlsoap.org/soap/envelope/", "Fault");
    body.appendChild(fault);

    Element faultCode = faultDoc.createElement("faultcode");
    fault.appendChild(faultCode);

    Element faultString = faultDoc.createElement("faultstring");
    fault.appendChild(faultString);

    Text textCode = faultDoc.createTextNode(code);
    faultCode.appendChild(textCode);

    Text textString = faultDoc.createTextNode(message);
    faultString.appendChild(textString);
    
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
