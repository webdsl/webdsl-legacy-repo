import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.math.BigInteger;


public class FibonacciMathMLDOM {

  public static void main(String[] args) {

    try {

      // Find the implementation
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();
      
      // Create the document
      DocumentType mathml = impl.createDocumentType("math",
       "-//W3C//DTD MathML 2.0//EN",
       "http://www.w3.org/TR/MathML2/dtd/mathml2.dtd");
      Document doc = impl.createDocument(
       "http://www.w3.org/1998/Math/MathML", "math", mathml);
       
      // Fill the document
      BigInteger low  = BigInteger.ONE;
      BigInteger high = BigInteger.ONE;

      Element root = doc.getDocumentElement();

      for (int i = 1; i <= 10; i++) {
        Element mrow = doc.createElement("mrow");
        
        Element mi = doc.createElement("mi");
        Text function = doc.createTextNode("f(" + i + ")");
        mi.appendChild(function);
        
        Element mo = doc.createElement("mo");
        Text equals = doc.createTextNode("=");
        mo.appendChild(equals);
        
        Element mn = doc.createElement("mn");
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
      TransformerFactory xformFactory 
       = TransformerFactory.newInstance();  
      Transformer idTransform = xformFactory.newTransformer();
      Source input = new DOMSource(doc);
      Result output = new StreamResult(System.out);
      idTransform.transform(input, output);
      
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
    catch (TransformerConfigurationException e) {
      System.err.println(e); 
    }
    catch (TransformerException e) {
      System.err.println(e); 
    }
    
  }

}
