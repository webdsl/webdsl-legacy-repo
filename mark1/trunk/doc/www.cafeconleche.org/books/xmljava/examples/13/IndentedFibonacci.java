import java.math.*;
import java.io.IOException;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.xml.serialize.*;


public class IndentedFibonacci {

  public static void main(String[] args) {

    try {

      // Find the implementation
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();
      
      // Create the document
      Document doc = impl.createDocument(null, 
       "Fibonacci_Numbers", null);
       
      // Fill the document
      BigInteger low  = BigInteger.ONE;
      BigInteger high = BigInteger.ONE;

      Element root = doc.getDocumentElement();

      for (int i = 0; i < 10; i++) {
        Element number = doc.createElement("fibonacci");
        Text text = doc.createTextNode(low.toString());
        number.appendChild(text);
        root.appendChild(number);

        BigInteger temp = high;
        high = high.add(low);
        low = temp;
      }

      // Serialize the document
      OutputFormat format = new OutputFormat(doc);
      format.setLineWidth(65);
      format.setIndenting(true);
      format.setIndent(2);
      XMLSerializer serializer = new XMLSerializer(System.out, format);
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
