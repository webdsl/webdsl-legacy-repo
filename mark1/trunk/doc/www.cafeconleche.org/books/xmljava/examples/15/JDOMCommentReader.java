import org.jdom.*;
import org.jdom.input.SAXBuilder;
import java.util.*;
import java.io.IOException;


public class JDOMCommentReader {

  public static void main(String[] args) {
    
    if (args.length <= 0) {
      System.out.println("Usage: java JDOMCommentReader url");
      return; 
    }
    SAXBuilder builder = new SAXBuilder();
    
    try {
      // Read the entire document into memory
      Document doc = builder.build(args[0]);
      List content = doc.getContent();
      Iterator iterator = content.iterator();
      while (iterator.hasNext()) {
        Object o = iterator.next();
        if (o instanceof Comment) {
          Comment c = (Comment) o;
          System.out.println(c.getText());     
          System.out.println();     
        }
        else if (o instanceof Element) {
          processElement((Element) o);   
        }
      }
    }
    catch (JDOMException e) {
      System.err.println(e); 
    }
    catch (IOException e) {
      System.err.println(e); 
    }
        
  } // end main

  // note use of recursion
  public static void processElement(Element element) {
    
    List content = element.getContent();
    Iterator iterator = content.iterator();
    while (iterator.hasNext()) {
      Object o = iterator.next();
      if (o instanceof Comment) {
        Comment c = (Comment) o;
        System.out.println(c.getText());     
        System.out.println();     
      }
      else if (o instanceof Element) {
        processElement((Element) o);   
      }
    } // end while
    
  }

}
