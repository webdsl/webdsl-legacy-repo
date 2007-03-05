import org.w3c.dom.*;
import org.apache.xerces.dom3.*;
import org.apache.xerces.dom3.ls.DOMWriter;
import org.apache.xml.serialize.XMLSerializer;
import java.io.IOException;
import javax.xml.parsers.*;


public class SVGCircle {

  public static void main(String[] args) {
     
    try {
      // Find the implementation
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();
      
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
      DOMWriter writer = new XMLSerializer();
      writer.setNewLine("\r\n");
      writer.setEncoding("UTF-16");
      writer.setErrorHandler(
        new DOMErrorHandler() {
          public boolean handleError(DOMError error) {
            System.err.println(error.getMessage());
            return false;
          }
        }
      );
      writer.writeNode(System.out, doc);
      
    }
    catch (Exception e) {
      System.err.println(e);
    }
  
  }

}
