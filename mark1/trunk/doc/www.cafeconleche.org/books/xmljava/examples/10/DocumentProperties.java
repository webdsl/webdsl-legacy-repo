import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class DocumentProperties {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java DocumentProperties URL"); 
      return;
    }
    String url = args[0];
    
    // Since this only works in Xerces 2.0.2, we might as well use the 
    // Xerces-specific implementation classes instead of JAXP.
    DOMParser parser = new DOMParser();
    try {
      parser.parse(url); 
      DocumentImpl document = (DocumentImpl) parser.getDocument();
      
      // DOM2 properties
      System.out.println("Implementation: " + document.getImplementation());
      System.out.println("Root element: " + document.getDocumentElement());
      System.out.println("DOCTYPE: " + document.getDoctype());

      // DOM3 Properties
      System.out.println("Version: " + document.getVersion());
      System.out.println("Standalone: " + document.getStandalone());
      System.out.println("Declared encoding: " + document.getEncoding());
      System.out.println("Strict error checking: " + document.getStrictErrorChecking());
      System.out.println("Actual encoding: " + document.getActualEncoding());
      System.out.println("Base URI: " + document.getBaseURI());

    }
    catch (SAXException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not read " + url
      ); 
    }
   
  }

}
