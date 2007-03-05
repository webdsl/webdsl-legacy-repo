import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.IOException;


public class JDOMValidator {

  public static void main(String[] args) {
  
    if (args.length == 0) {
      System.out.println("Usage: java JDOMValidator URL"); 
      return;
    } 
      
    SAXBuilder builder = new SAXBuilder(true);
                                    //  ^^^^
                                    // Turn on validation
     
    // command line should offer URIs or file names
    try {
      builder.build(args[0]);
      // If there are no well-formedness or validity errors, 
      // then no exception is thrown.
      System.out.println(args[0] + " is valid.");
    }
    // indicates a well-formedness or validity error
    catch (JDOMException e) { 
      System.out.println(args[0] + " is not valid.");
      System.out.println(e.getMessage());
    }  
    catch (IOException e) { 
      System.out.println("Could not check " + args[0]);
      System.out.println(" because " + e.getMessage());
    }  
  
  }

}
