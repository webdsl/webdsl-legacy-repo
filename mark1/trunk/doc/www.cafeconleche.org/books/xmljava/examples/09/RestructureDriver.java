import javax.xml.parsers.*; // JAXP
import javax.xml.transform.*; // JAXP
import javax.xml.transform.dom.DOMSource; // JAXP
import javax.xml.transform.stream.StreamResult; // JAXP
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import java.io.IOException;


public class RestructureDriver {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java RestructureDriver URL");
      return;
    }
    String url = args[0];
    
    try {
      // Find a parser
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder parser = factory.newDocumentBuilder();
      
      // Read the document
      Document document = parser.parse(url); 
     
      // Modify the document
      Restructurer.processNode(document);
      
      // Write it out again
      TransformerFactory xformFactory 
       = TransformerFactory.newInstance();
      Transformer idTransform = xformFactory.newTransformer();
      Source input = new DOMSource(document);
      Result output = new StreamResult(System.out);
      idTransform.transform(input, output);
      
    }
    catch (SAXException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not read " + url
      ); 
    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    }
    catch (TransformerConfigurationException e) { 
      System.out.println("This DOM does not support transforms."); 
    }
    catch (TransformerException e) { 
      System.out.println("Transform failed."); 
    }
   
  }

}
