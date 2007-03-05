import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class DOMCommentReader {

  // note use of recursion
  public static void printComments(Node node) {
    
    int type = node.getNodeType();
    if (type == Node.COMMENT_NODE) {
      Comment comment = (Comment) node;
      System.out.println(comment.getData());
      System.out.println();
    }
    else {
      if (node.hasChildNodes()) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
          printComments(children.item(i));
        } 
      }
    }
    
  }

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java DOMCommentReader URL");
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
      DOMCommentReader.printComments(document);

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
