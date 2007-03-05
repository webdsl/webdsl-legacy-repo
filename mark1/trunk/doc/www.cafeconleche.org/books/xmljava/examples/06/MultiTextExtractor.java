import org.xml.sax.*;
import java.util.List;


public class MultiTextExtractor implements ContentHandler {

  private List documents;
  
  // This field is deliberately not initialized in the
  // constructor. It is initialized for each document parsed, not
  // for each object constructed.
  private StringBuffer currentDocument;
  
  public MultiTextExtractor(List documents) {
    
    if (documents == null) {
      throw new NullPointerException(
       "Documents list must be non-null");
    }
    this.documents = documents;   
  }

  // Initialize the per-document data structures
  public void startDocument() {
    
    currentDocument = new StringBuffer();
    
  }
  
  // Flush and commit the per-document data structures
  public void endDocument() {
    
    String text = currentDocument.toString();
    documents.add(text);
    
  }
    
  // Update the per-document data structures
  public void characters(char[] text, int start, int length) {

    currentDocument.append(text, start, length); 
      
  }  
    
  // do-nothing methods
  public void setDocumentLocator(Locator locator) {}
  public void startPrefixMapping(String prefix, String uri) {}
  public void endPrefixMapping(String prefix) {}
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) {}
  public void endElement(String namespaceURI, String localName,
   String qualifiedName) {}
  public void ignorableWhitespace(char[] text, int start, 
   int length) {}
  public void processingInstruction(String target, 
   String data) {}
  public void skippedEntity(String name) {}

}
