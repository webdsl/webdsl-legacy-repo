import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class CommentIterator {

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
      
      // Check for the traversal module
      DOMImplementation impl = parser.getDOMImplementation();
      if (!impl.hasFeature("traversal", "2.0")) {
        System.out.println(
         "A DOM implementation that supports traversal is required."
        );  
        return;
      }
      
      // Read the document
      Document doc = parser.parse(url); 
      
      // Create the NodeIterator
      DocumentTraversal traversable = (DocumentTraversal) doc;
      NodeIterator iterator = traversable.createNodeIterator(
       doc, NodeFilter.SHOW_COMMENT, null, true);

      // Iterate over the comments
      Node node;
      while ((node = iterator.nextNode()) != null) {
        System.out.println(node.getNodeValue());
      }
       
    }
    catch (SAXException e) {
      System.out.println(e);
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
