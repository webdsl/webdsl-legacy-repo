import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class NotationLister {

  // No recursion for a change. We don't need to walk the tree.
  public static void listNotations(Document doc) {
    
    DocumentType doctype   = doc.getDoctype();
    NamedNodeMap notations = doctype.getNotations();
    for (int i = 0; i < notations.getLength(); i++) {
      
      Notation notation = (Notation) notations.item(i);
      
      String name       = notation.getNodeName();
      String publicID   = notation.getPublicId();
      String systemID   = notation.getSystemId();
      
      System.out.print(name + ": ");
      if (publicID != null) System.out.print(publicID + " ");
      if (systemID != null) System.out.print(systemID + " ");
      System.out.println();
      
    } 
    
  }

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java NotationLister URL");
      return;
    }
    String url = args[0];
    
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();
      
      // Read the document
      Document document = parser.parse(url); 
      
      // Process the document
      listNotations(document);

    }
    catch (SAXException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not check " + url
      ); 
    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    }
     
  } // end main
  
}
