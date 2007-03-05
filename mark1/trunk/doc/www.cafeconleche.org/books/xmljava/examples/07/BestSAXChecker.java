import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;


public class BestSAXChecker implements ErrorHandler {
  
  public void warning(SAXParseException exception) {
    
    System.out.println("Warning: " + exception.getMessage());
    System.out.println(" at line " + exception.getLineNumber() 
     + ", column " + exception.getColumnNumber());
    System.out.println(" in entity " + exception.getSystemId());
    
  }
  
  public void error(SAXParseException exception) {
     
    System.out.println("Error: " + exception.getMessage());
    System.out.println(" at line " + exception.getLineNumber() 
     + ", column " + exception.getColumnNumber());
    System.out.println(" in entity " + exception.getSystemId());
    
  }
  
  public void fatalError(SAXParseException exception) {
     
    System.out.println("Fatal Error: " + exception.getMessage());
    System.out.println(" at line " + exception.getLineNumber() 
     + ", column " + exception.getColumnNumber()); 
    System.out.println(" in entity " + exception.getSystemId());
     
  }
  
  public static void main(String[] args) {
  
    if (args.length <= 0) {
      System.out.println("Usage: java BestSAXChecker URL");
      return;
    }
    String document = args[0];
    
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader();
      ErrorHandler handler = new BestSAXChecker();
      parser.setErrorHandler(handler);
      parser.parse(document);
      // If the document isn't well-formed, an exception has
      // already been thrown and this has been skipped.
      System.out.println(document + " is well-formed.");
    }
    catch (SAXParseException e) {
      System.out.print(document + " is not well-formed at ");
      System.out.println("Line " + e.getLineNumber() 
       + ", column " +  e.getColumnNumber() );
      System.out.println(" in entity " + e.getSystemId());
    }
    catch (SAXException e) {
      System.out.println("Could not check document because " 
       + e.getMessage());
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not check " 
       + document
      ); 
    }
  
  }

}
