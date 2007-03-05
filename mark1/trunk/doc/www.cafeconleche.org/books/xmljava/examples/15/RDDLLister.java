import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import java.util.*;
import java.io.IOException;


public class RDDLLister {
  
  public final static Namespace XLINK_NAMESPACE = 
   Namespace.getNamespace("xl", "http://www.w3.org/1999/xlink");
  public final static String RDDL_NAMESPACE 
   = "http://www.rddl.org/";

  public static void main(String[] args) {
    
    if (args.length <= 0) {
      System.out.println("Usage: java RDDLLister url");
      return; 
    }
    
    SAXBuilder builder = new SAXBuilder();
    
    try {
      // Prepare the output document
      Element html = new Element("html");
      Element body = new Element("body");
      Element table = new Element("table");
      html.addContent(body);
      body.addContent(table);
      Document output = new Document(html);
      
      // Read the entire document into memory
      Document doc = builder.build(args[0]);
      Element root = doc.getRootElement();
      processElement(root, table); 
      
      // Serialize the output document
      XMLOutputter outputter = new XMLOutputter("  ", true);
      outputter.output(output, System.out);
      
    }
    catch (JDOMException e) {
      System.err.println(e); 
    }
    catch (IOException e) {
      System.err.println(e); 
    }
        
  } // end main

  public static void processElement(Element input, Element output) {
    
    if (input.getName().equals("resource") 
     && input.getNamespaceURI().equals(RDDL_NAMESPACE)) {
     
       String href    = input.getAttributeValue("href", XLINK_NAMESPACE);
       String title   = input.getAttributeValue("title", XLINK_NAMESPACE);
       String role    = input.getAttributeValue("role", XLINK_NAMESPACE);
       String arcrole = input.getAttributeValue("arcrole", XLINK_NAMESPACE);
     
       // Wrap this up in a table row
       Element tr = new Element("tr");
       
       Element titleCell = new Element("td");
       titleCell.setText(title);
       tr.addContent(titleCell);
       
       Element hrefCell = new Element("td");
       Element a = new Element("a");
       a.setAttribute("href", href);
       a.setText(href);
       hrefCell.addContent(a);
       tr.addContent(hrefCell);
       
       Element roleCell = new Element("td");
       roleCell.setText(role);
       tr.addContent(roleCell);
       
       Element arcroleCell = new Element("td");
       arcroleCell.setText(arcrole);
       tr.addContent(arcroleCell);
  
       output.addContent(tr);       
     
    }
    
    // Recurse
    List content = input.getContent();
    Iterator iterator = content.iterator();
    while (iterator.hasNext()) {
      Object o = iterator.next();
      if (o instanceof Element) {
        processElement((Element) o, output);   
      }
    } // end while
    
  }

}
