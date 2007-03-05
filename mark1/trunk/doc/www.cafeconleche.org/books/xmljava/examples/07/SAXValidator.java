import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;


public class SAXValidator implements ErrorHandler {
  
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
    // Well-formedness is a prerequisite for validity
    valid = false;
    
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
     
  }
  

  public static void main(String[] args) {
  
    if (args.length <= 0) {
      System.out.println("Usage: java SAXValidator URL");
      return;
    }
    String document = args[0];
    
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader();
      SAXValidator handler = new SAXValidator();
      parser.setErrorHandler(handler);
      // Turn on validation. 
      parser.setFeature(
       "http://xml.org/sax/features/validation", true);
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
       + ", column " +  e.getColumnNumber() );
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
