import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;


public class BetterSAXChecker {

  public static void main(String[] args) {
  
    if (args.length <= 0) {
      System.out.println("Usage: java BetterSAXChecker URL");
      return;
    }
    String document = args[0];
    
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader();
      parser.parse(document);
      System.out.println(document + " is well-formed.");
    }
    catch (SAXParseException e) {
      System.out.print(document + " is not well-formed at ");
      System.out.print("Line " + e.getLineNumber() 
       + ", column " +  e.getColumnNumber() );
      System.out.println(" in the entity " + e.getSystemId());
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
