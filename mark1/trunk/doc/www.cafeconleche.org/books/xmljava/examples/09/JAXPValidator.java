import javax.xml.parsers.*; // JAXP
import org.xml.sax.*;
import java.io.IOException;


public class JAXPValidator {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java JAXPValidator URL");
      return;
    }
    String document = args[0];
    
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      // Always turn on namespace awareness
      factory.setNamespaceAware(true);
      // Turn on validation
      factory.setValidating(true);

      DocumentBuilder parser = factory.newDocumentBuilder();
      
      // SAXValidator was developed in Chapter 7
      ErrorHandler handler = new SAXValidator();
      parser.setErrorHandler(handler);
      
      parser.parse(document); 
      if (handler.isValid()) {
        System.out.println(document + " is valid.");
      }
      else {
        // If the document isn't well-formed, an exception has
        // already been thrown and this has been skipped.
        System.out.println(document + " is well-formed.");
      }
      
    }
    catch (SAXException e) {
      System.out.println(document + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not check " 
       + document
      ); 
    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    }
   
  }

}
