import javax.xml.parsers.*;  // JAXP
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.IOException;


public class TreeReporter {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java TreeReporter URL");
      return; 
    }
     
    TreeReporter iterator = new TreeReporter();
    try {
      // Use JAXP to find a parser
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      // Turn on namespace support
      factory.setNamespaceAware(true);
      DocumentBuilder parser = factory.newDocumentBuilder();
      
      // Read the entire document into memory
      Node document = parser.parse(args[0]); 
      
      // Process it starting at the root
      iterator.followNode(document);

    }
    catch (SAXException e) {
      System.out.println(args[0] + " is not well-formed.");
      System.out.println(e.getMessage());
    }   
    catch (IOException e) { 
      System.out.println(e); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    }
  
  } // end main

  private PropertyPrinter printer = new PropertyPrinter();
  
  // note use of recursion
  public void followNode(Node node) throws IOException {
    
    printer.writeNode(node);
    if (node.hasChildNodes()) {
      Node firstChild = node.getFirstChild();
      followNode(firstChild);
    }
    Node nextNode = node.getNextSibling();
    if (nextNode != null) followNode(nextNode);
    
  }

}
