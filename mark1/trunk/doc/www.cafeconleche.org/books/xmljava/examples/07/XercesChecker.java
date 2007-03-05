import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;


public class XercesChecker implements ErrorHandler {
  
  // Flag to check whether any errors have been spotted.
  private boolean valid = true;
  
  public boolean isValid() {
    return valid; 
  }
  
  // If this handler is used to parse more than one document, 
  // its initial state needs to be reset between parses.
  public void reset() {
    // Assume document is valid until proven otherwise
    valid = true; 
  }
  
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
    // Unfortunately there's no good way to distinguish between
    // validity errors and other kinds of non-fatal errors 
    valid = false;
    
  }
  
  public void fatalError(SAXParseException exception) {
     
    System.out.println("Fatal Error: " + exception.getMessage());
    System.out.println(" at line " + exception.getLineNumber() 
     + ", column " + exception.getColumnNumber()); 
    System.out.println(" in entity " + exception.getSystemId());
     
  }
  
  public static void main(String[] args) {
  
    if (args.length <= 0) {
      System.out.println("Usage: java XercesChecker URL");
      return;
    }
    String document = args[0];
    
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader(
       "org.apache.xerces.parsers.SAXParser"
      );
      XercesChecker handler = new XercesChecker();
      parser.setErrorHandler(handler);
      
      // This is a hack to fit some long lines of code that 
      // follow between the margins of this printed page
      String features = "http://apache.org/xml/features/";
      
      // Turn on Xerces specific features
      parser.setFeature(features + "validation/dynamic", true);
      parser.setFeature(features 
       + "validation/schema-full-checking", true); 
      parser.setFeature(features 
       + "validation/warn-on-duplicate-attdef", true);
      parser.setFeature(features 
       + "validation/warn-on-undeclared-elemdef", true);
      parser.setFeature(features + "continue-after-fatal-error", 
       true); 
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
    catch (SAXParseException e) {
      System.out.print(document + " is not well-formed at ");
      System.out.println("Line " + e.getLineNumber() 
       + ", column " +  e.getColumnNumber() 
       + " in file " + e.getSystemId());
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
