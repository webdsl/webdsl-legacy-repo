import org.w3c.dom.*;
import org.w3c.dom.ls.*;


public class DOM3Checker {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java DOM3Checker URL");
      return;
    }
    String document = args[0];
    
    try {
      DOMImplementationLS impl = (DOMImplementationLS) 
       DOMImplementationRegistry
       .getDOMImplementation("LS-Load 3.0");
      DOMBuilder parser = impl.createDOMBuilder(
       DOMImplementationLS.MODE_SYNCHRONOUS,
       "http://www.w3.org/TR/REC-xml");
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // Use DTDs when parsing
      Document doc = parser.parseURI(document); 
      System.out.println(document + " is well-formed.");
    }
    catch (NullPointerException e) {
      System.err.println("The current DOM implementation does"
       + " not support DOM Level 3 Load and Save");
    }
    catch (DOMException e) {
      System.err.println(document + " is not well-formed");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not check " 
       + document
      ); 
    }
    catch (Exception e) {
      // Probably a ClassNotFoundException,
      // InstantiationException, or IllegalAccessException 
      // thrown by DOMImplementationRegistry.getDOMImplementation
      System.out.println("Probable CLASSPATH problem."); 
      e.printStackTrace(); 
    }
   
  }

}
