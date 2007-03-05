import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class ExampleList {

  public static void printExampleTitles(Document doc) {
  
    // Create the NodeIterator
    DocumentTraversal traversable = (DocumentTraversal) doc;
    TreeWalker walker = traversable.createTreeWalker(
     doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, 
     new ExampleFilter(), true);
    
    // The TreeWalker starts out positioned at the root     
    Node chapter = walker.firstChild();
    int chapterNumber = 0;
    while (chapter != null) {
      chapterNumber++;
      Node example = walker.firstChild();
      int exampleNumber = 0;
      while (example != null) {
        exampleNumber++;
        Node title = walker.firstChild();
        String titleText = TextExtractor.getText(title);
        titleText = "Example " + chapterNumber + "."
         + exampleNumber + ": " + titleText;
        System.out.println(titleText);
        // Back up to the example
        walker.parentNode();
        example = walker.nextSibling();
      }
      // Reposition the walker on the parent chapter
      walker.parentNode();
      // Go to the next chapter
      chapter = walker.nextSibling();
    }
    
  }
  
  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java ExampleList URL");
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
      printExampleTitles(doc);
       
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
