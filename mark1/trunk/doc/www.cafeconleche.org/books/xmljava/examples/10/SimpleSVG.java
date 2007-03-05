import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.*;
import java.io.IOException;


public class SimpleSVG {

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
      ProcessingInstruction xmlstylesheet 
       = doc.createProcessingInstruction("xml-stylesheet",
       "type=\"text/css\" href=\"standard.css\"");
      Comment comment = doc.createComment(
       "An example from Chapter 10 of Processing XML with Java");
      doc.insertBefore(comment, rootElement);
      doc.insertBefore(xmlstylesheet, rootElement);
      Node desc = doc.createElementNS(
       "http://www.w3.org/2000/svg", "desc");
      rootElement.appendChild(desc);
      Text descText = doc.createTextNode(
       "An example from Processing XML with Java");
      desc.appendChild(descText);
      
      // Serialize the document onto System.out
      TransformerFactory xformFactory 
       = TransformerFactory.newInstance();  
      Transformer idTransform = xformFactory.newTransformer();
      Source input = new DOMSource(doc);
      Result output = new StreamResult(System.out);
      idTransform.transform(input, output);

    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a JAXP factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println(
        "Could not locate a JAXP DocumentBuilder class"
      ); 
    }
    catch (DOMException e) {
      System.err.println(e); 
    }
    catch (TransformerConfigurationException e) {
      System.err.println(e); 
    }
    catch (TransformerException e) {
      System.err.println(e); 
    }
  
  }

}
