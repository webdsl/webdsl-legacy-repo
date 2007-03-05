import java.math.*;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.xml.serialize.*;


public class ValidFibonacciMathML {

  public static String MATHML_NS 
   = "http://www.w3.org/1998/Math/MathML";  
    
  public static void main(String[] args) {

    try {

      // Find the implementation
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();
      
      // Create the document
      Document doc 
       = impl.createDocument(MATHML_NS, "math", null);
       
      // Fill the document
      BigInteger low  = BigInteger.ONE;
      BigInteger high = BigInteger.ONE;

      Element root = doc.getDocumentElement();
      root.setAttribute("xmlns", MATHML_NS);

      for (int i = 1; i <= 10; i++) {
        Element mrow = doc.createElementNS(MATHML_NS, "mrow");
        
        Element mi = doc.createElementNS(MATHML_NS, "mi");
        Text function = doc.createTextNode("f(" + i + ")");
        mi.appendChild(function);
        
        Element mo = doc.createElementNS(MATHML_NS, "mo");
        Text equals = doc.createTextNode("=");
        mo.appendChild(equals);
        
        Element mn = doc.createElementNS(MATHML_NS, "mn");
        Text value = doc.createTextNode(low.toString());
        mn.appendChild(value);
        
        mrow.appendChild(mi);
        mrow.appendChild(mo);
        mrow.appendChild(mn);
        
        root.appendChild(mrow);

        BigInteger temp = high;
        high = high.add(low);
        low = temp;
      }
      
      // Serialize the document onto System.out
      OutputFormat format = new OutputFormat(doc);
      format.setLineWidth(65);
      format.setIndenting(true);
      format.setIndent(2);
      format.setEncoding("ISO-8859-1");
      format.setDoctype("-//W3C//DTD MathML 2.0//EN",
       "http://www.w3.org/TR/MathML2/dtd/mathml2.dtd"); 
      format.setMediaType("application/xml");
      format.setOmitComments(true);
      format.setOmitXMLDeclaration(false);
      format.setVersion("1.0");
      format.setStandalone(true);
      
      XMLSerializer serializer 
       = new XMLSerializer(System.out, format);
      serializer.serialize(doc);
      
    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a JAXP factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println(
        "Could not locate a JAXP DocumentBuilder class"
      ); 
    }
    catch (DOMException e) {
      System.err.println(e); 
    }
    catch (IOException e) {
      System.err.println(e); 
    }
    
  }

}
