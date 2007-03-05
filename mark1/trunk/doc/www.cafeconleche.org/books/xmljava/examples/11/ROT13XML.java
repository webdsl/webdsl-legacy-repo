import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.IOException;


public class ROT13XML {

  // note use of recursion
  public static void encode(Node node) {
    
    if (node instanceof CharacterData) {
      CharacterData text = (CharacterData) node;
      String data = text.getData();
      text.setData(rot13(data));
    }
    
    // recurse the children
    if (node.hasChildNodes()) {
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        encode(children.item(i));
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
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();
      
      // Read the document
      Document document = parser.parse(url); 
      
      // Modify the document
      ROT13XML.encode(document);

      // Write it out again
      TransformerFactory xformFactory 
       = TransformerFactory.newInstance();
      Transformer idTransform = xformFactory.newTransformer();
      Source input = new DOMSource(document);
      Result output = new StreamResult(System.out);
      idTransform.transform(input, output);

    }
    catch (SAXException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
      "Due to an IOException, the parser could not encode " + url
      ); 
    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    }
    catch (TransformerConfigurationException e) { 
      System.out.println("Could not locate a TrAX transformer"); 
    }
    catch (TransformerException e) { 
      System.out.println("Could not transform"); 
    }
     
  } // end main

}
