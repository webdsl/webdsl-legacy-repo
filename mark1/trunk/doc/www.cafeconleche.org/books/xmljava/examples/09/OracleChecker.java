import oracle.xml.parser.v2.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class OracleChecker {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java OracleChecker URL"); 
      return;
    }
    String document = args[0];
    
    DOMParser parser = new DOMParser();
    try {
      parser.parse(document); 
      System.out.println(document + " is well-formed.");
    }
    catch (XMLParseException e) {
      System.out.println(document + " is not well-formed.");
      System.out.println(e);
      
    }
    catch (SAXException e) {
      System.out.println(document + " could not be parsed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not check " 
       + document
      ); 
    }
   
  }

}
