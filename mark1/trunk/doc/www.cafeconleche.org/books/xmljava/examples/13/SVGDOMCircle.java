import org.w3c.dom.*;
import org.w3c.dom.ls.*;


public class SVGDOMCircle {

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
      
      // Create the document
      DocumentType svgDOCTYPE = impl.createDocumentType(
       "svg", "-//W3C//DTD SVG 1.0//EN", 
       "http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd"
      );
      Document doc = impl.createDocument(
       "http://www.w3.org/2000/svg", "svg", svgDOCTYPE);
       
      // Fill the document
      Node rootElement = doc.getDocumentElement();
      Element circle = doc.createElementNS(
       "http://www.w3.org/2000/svg", "circle");
      circle.setAttribute("r", "100");
      rootElement.appendChild(circle);

      // Serialize the document onto System.out
      DOMImplementationLS implls = (DOMImplementationLS) impl;
      DOMWriter writer = implls.createDOMWriter();
      writer.writeNode(System.out, doc);
      
    }
    catch (Exception e) {
      System.err.println(e);
    }
  
  }

}
