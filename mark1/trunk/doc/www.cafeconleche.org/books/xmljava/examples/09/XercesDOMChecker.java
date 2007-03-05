import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXException;
import java.io.IOException;


public class XercesChecker {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java XercesChecker URL"); 
      return;
    }
    String document = args[0];
    
    DOMParser parser = new DOMParser();
    try {
      parser.parse(document); 
      System.out.println(document + " is well-formed.");
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
   
  }

}
