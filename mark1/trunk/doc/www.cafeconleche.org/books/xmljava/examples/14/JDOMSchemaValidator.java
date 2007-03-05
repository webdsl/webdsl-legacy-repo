import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.IOException;


public class JDOMSchemaValidator {

  public static void main(String[] args) {
  
    if (args.length == 0) {
      System.out.println("Usage: java JDOMSchemaValidator URL"); 
      return;
    } 
      
    SAXBuilder builder = new SAXBuilder(
     "org.apache.xerces.parsers.SAXParser");
    builder.setValidation(true);
    builder.setErrorHandler(new BestSAXChecker());
                             // ^^^^^^^^^^^^^^
                             // From Chapter 7
    // turn on schema support
    builder.setFeature(
      "http://apache.org/xml/features/validation/schema", true);                         
                             
    // command line should offer URIs or file names
    try {
      builder.build(args[0]);
    }
    // indicates a well-formedness error
    catch (JDOMException e) { 
      System.out.println(args[0] + " is not well-formed.");
      System.out.println(e.getMessage());
    }  
    catch (IOException e) { 
      System.out.println("Could not check " + args[0]);
      System.out.println(" because " + e.getMessage());
    }  
  
  }

}
