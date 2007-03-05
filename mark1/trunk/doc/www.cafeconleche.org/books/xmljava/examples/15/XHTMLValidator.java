import java.io.IOException;
import org.jdom.*;
import org.jdom.input.SAXBuilder;


public class XHTMLValidator {

  public static void main(String[] args) {
    
    for (int i = 0; i < args.length; i++) {
      validate(args[i]);
    }   
    
  }

  private static SAXBuilder builder = new SAXBuilder(true);
                               /* turn on validation ^^^^ */
  
  // not thread safe
  public static void validate(String source) {
        
      Document document;
      try {
        document = builder.build(source); 
      }
      catch (JDOMException e) {  
        System.out.println(source 
         + " is invalid XML, and thus not XHTML."); 
        return; 
      }
      catch (IOException e) {  
        System.out.println("Could not read: " + source); 
        return; 
      }
      
      // If we get this far, then the document is valid XML.
      // Check to see whether the document is actually XHTML 
      boolean valid = true;       
      DocType doctype = document.getDocType();
    
      if (doctype == null) {
        System.out.println("No DOCTYPE");
        valid = false;
      }
      else {
        // verify the DOCTYPE
        String name     = doctype.getElementName();
        String systemID = doctype.getSystemID();
        String publicID = doctype.getPublicID();
      
        if (!name.equals("html")) {
          System.out.println(
           "Incorrect root element name " + name);
          valid = false;
        }
    
        if (publicID == null
         || (!publicID.equals("-//W3C//DTD XHTML 1.0 Strict//EN")
           && !publicID.equals(
            "-//W3C//DTD XHTML 1.0 Transitional//EN")
           && !publicID.equals(
            "-//W3C//DTD XHTML 1.0 Frameset//EN"))) {
          valid = false;
          System.out.println(source 
           + " does not seem to use an XHTML 1.0 DTD");
        }
      }
    
    
      // Check the namespace on the root element
      Element root = document.getRootElement();
      Namespace namespace = root.getNamespace();
      String prefix = namespace.getPrefix();
      String uri = namespace.getURI();
      if (!uri.equals("http://www.w3.org/1999/xhtml")) {
        valid = false;
        System.out.println(source 
         + " does not properly declare the"
         + " http://www.w3.org/1999/xhtml namespace"
         + " on the root element");        
      }
      if (!prefix.equals("")) {
        valid = false;
        System.out.println(source 
         + " does not use the empty prefix for XHTML");        
      }
      
      if (valid) System.out.println(source + " is valid XHTML.");
    
  }

}
