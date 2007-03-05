import org.jdom.*;
import org.jdom.output.XMLOutputter;
import org.jdom.input.SAXBuilder;
import java.io.IOException;
import java.util.*;


public class ROT13XML {

  // note use of recursion
  public static void encode(Element element) {
    
    List content = element.getContent();
    Iterator iterator = content.iterator();
    while (iterator.hasNext()) {
      Object o = iterator.next();
      if (o instanceof Text) {
        Text t = (Text) o;
        String cipherText = rot13(t.getText());
        t.setText(cipherText);
      }
      else if (o instanceof Element) {
        Element child = (Element) o;
        encode(child);
      }
    }
    
  }
  
  public static String rot13(String s) {
    
    StringBuffer out = new StringBuffer(s.length());
    for (int i = 0; i < s.length(); i++) {
      int c = s.charAt(i);
      if (c >= 'A' && c <= 'M') out.append((char) (c+13));
      else if (c >= 'N' && c <= 'Z') out.append((char) (c-13));
      else if (c >= 'a' && c <= 'm') out.append((char) (c+13));
      else if (c >= 'n' && c <= 'z') out.append((char) (c-13));
      else out.append((char) c);
    } 
    return out.toString();
    
  }

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java ROT13XML URL");
      return;
    }
    
    String url = args[0];
    
    try {
      SAXBuilder parser = new SAXBuilder();
      
      // Read the document
      Document document = parser.build(url); 
      
      // Modify the document
      ROT13XML.encode(document.getRootElement());

      // Write it out again
      XMLOutputter outputter = new XMLOutputter();
      outputter.output(document, System.out);
    }
    catch (JDOMException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not encode " + url
      ); 
    }
     
  } // end main

}
