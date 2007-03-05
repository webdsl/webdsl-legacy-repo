import org.w3c.dom.*;
import org.w3c.dom.ls.*;


public class XHTMLPurifier {
  
  public static void main(String[] args) {
     
    try {
      // Find the implementation
      DOMImplementation impl 
       = DOMImplementationRegistry.getDOMImplementation(
          "Core 2.0 LS-Load 3.0 LS-Save 3.0");
      if (impl == null) {
        System.out.println(
         "Could not find a DOM3 Load-Save compliant parser.");
        return;
      }
      DOMImplementationLS implls = (DOMImplementationLS) impl;

      // Load the parser
      DOMBuilder parser = implls.createDOMBuilder(
       DOMImplementationLS.MODE_SYNCHRONOUS);
      
      // Parse the document
      Document doc = parser.parseURI(document);
      
      // Serialize the document onto System.out while filtering
      DOMWriter writer = implls.createDOMWriter();
      DOMWriterFilter filter = new XHTMLFilter();
      writer.setFilter(filter);
      writer.writeNode(System.out, doc);
      
    }
    catch (Exception e) {
      System.err.println(e);
    }
  
  }
  
}
