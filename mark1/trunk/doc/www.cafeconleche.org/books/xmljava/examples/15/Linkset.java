import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import java.io.IOException;
import java.util.*;


public class Linkset {
  
  public static void main(String[] args) {
    
    String url = "http://www.slashdot.org/slashdot.rdf";
    
    try {
      SAXBuilder parser = new SAXBuilder();
      
      // Parse the document
      Document document = parser.build(url); 
      Element oldRoot = document.getRootElement();
      Element newRoot = new Element("linkset");
      List content = oldRoot.getChildren();
      Iterator iterator = content.iterator();
      while (iterator.hasNext()) {
        Object next = iterator.next();
        Element element = (Element) next; 
        Element link = element.getChild("link", 
         Namespace.getNamespace(
         "http://my.netscape.com/rdf/simple/0.9/"));
        link.detach();
        newRoot.addContent(link);
      }

      XMLOutputter outputter = new XMLOutputter("  ", true);
      outputter.output(newRoot, System.out);
    }
    catch (JDOMException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not read " + url
      ); 
    }
     
  } // end main

}
