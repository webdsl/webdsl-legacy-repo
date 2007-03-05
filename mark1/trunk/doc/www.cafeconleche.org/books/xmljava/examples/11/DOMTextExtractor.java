import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class DOMTextExtractor {

  public void processNode(Node node) {
    
    if (node instanceof Text) {
      Text text = (Text) node;
      String data = text.getData();
      System.out.println(data);
    }
    
  }

  // note use of recursion
  public void followNode(Node node) {
    
    processNode(node);
    if (node.hasChildNodes()) {
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        followNode(children.item(i));
      } 
    }
    
  }

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java DOMTextExtractor URL");
      return;
    }
    
    String url = args[0];
    
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();
      // If expandEntityReferences isn't turned off, there
      //  won't be any entity reference nodes in the DOM tree
      factory.setExpandEntityReferences(false);
      
      // Read the document
      Document document = parser.parse(url); 
      
      // Process the document
      DOMTextExtractor extractor = new DOMTextExtractor();
      extractor.followNode(document);

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
