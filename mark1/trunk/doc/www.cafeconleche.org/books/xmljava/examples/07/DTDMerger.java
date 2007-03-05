import org.xml.sax.*;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;


public class DTDMerger implements DeclHandler {

  public void elementDecl(String name, String model)
   throws SAXException {
    System.out.println("<!ELEMENT " + name + " " + model + " >");
  }
  
  public void attributeDecl(String elementName, 
   String attributeName, String type, String mode, 
   String defaultValue) throws SAXException {
     
    System.out.print("<!ATTLIST ");
    System.out.print(elementName);
    System.out.print(" ");
    System.out.print(attributeName);
    System.out.print(" ");
    System.out.print(type);
    System.out.print(" ");
    if (mode != null) {
      System.out.print(mode + " ");
    }
    if (defaultValue != null) {
      System.out.print('"' + defaultValue + "\" ");
    }
    System.out.println(">");   
     
  }
  
  public void internalEntityDecl(String name, 
   String value) throws SAXException {
     
    if (!name.startsWith("%")) { // ignore parameter entities
      System.out.println("<!ENTITY " + name + " \"" 
       + value + "\">");        
    }
    
  }
  
  public void externalEntityDecl(String name, 
   String publicID, String systemID) throws SAXException {
     
    if (!name.startsWith("%")) { // ignore parameter entities
      if (publicID != null) { 
        System.out.println("<!ENTITY " + name + " PUBLIC \"" 
         + publicID + "\" \"" + systemID + "\">");        
      
      }
      else {
        System.out.println("<!ENTITY " + name + " SYSTEM \"" 
         + systemID + "\">");        
      }
    }
    
  }

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java DTDMerger URL");
      return;
    }
    String document = args[0];
    
    XMLReader parser = null;
    try {
      parser = XMLReaderFactory.createXMLReader();
      DeclHandler handler = new DTDMerger();
      parser.setProperty(
       "http://xml.org/sax/properties/declaration-handler", 
       handler);
      parser.parse(document);
    }
    catch (SAXNotRecognizedException e) {
      System.err.println(parser.getClass() 
       + " does not support declaration handlers.");
    }
    catch (SAXNotSupportedException e) {
      System.err.println(parser.getClass() 
       + " does not support declaration handlers.");

    }
    catch (SAXException e) {
      System.err.println(e);
      // As long as we finished with the DTD we really don't care
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not check " 
       + document
      ); 
    }
   
  }
   
}
