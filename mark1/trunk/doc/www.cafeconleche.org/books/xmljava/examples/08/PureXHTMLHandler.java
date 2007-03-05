import org.xml.sax.*;
import java.util.*;


public class PureXHTMLHandler implements ContentHandler {

  public final static String XHTML_NAMESPACE 
   = "http://www.w3.org/1999/xhtml";
  
  private ContentHandler parent;
  private Stack namespaces;  // initialized in startDocument()
  
  public PureXHTMLHandler(ContentHandler parent) {
    this.parent = parent;
  }

  // Filter out anything that's not in the XHTML namespace 
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException {
     
    namespaces.push(namespaceURI);
    if (namespaceURI.equals(XHTML_NAMESPACE)) {
      parent.startElement(namespaceURI, localName, qualifiedName, 
       atts);
    }
    // else do nothing
    
  }
  
  // Filter out anything that's not in the XHTML namespace 
  public void endElement(String namespaceURI, String localName,
   String qualifiedName) throws SAXException {
     
    namespaces.pop();
    if (namespaceURI.equals(XHTML_NAMESPACE)) {
      parent.endElement(namespaceURI, localName, qualifiedName);
    }
    // else do-nothing
    
  }
  
  // simple utility method to determine whether or not the parent
  // element is an XHTML element
  private boolean inXHTML() {

    try {
      String namespace = (String) namespaces.peek();
      if (namespace.equals(XHTML_NAMESPACE)) return true;
      return false;
    }
    catch (EmptyStackException e) {
      // This means we're outside the root element in a 
      // processing instruction. Such processing instructions are
      // legal in XHTML so I keep it.
      return true;
    }
    
  }
  
  public void characters(char[] text, int start, int length)
   throws SAXException {
    if (inXHTML()) parent.characters(text, start, length); 
  }
  
  public void ignorableWhitespace(char[] text, int start, 
   int length) throws SAXException {
    if (inXHTML()) parent.ignorableWhitespace(text, start, length); 
  }
  
  public void processingInstruction(String target, String data)
   throws SAXException {
    if (inXHTML()) parent.processingInstruction(target, data); 
  }
  
  public void skippedEntity(String name)
   throws SAXException {
    if (inXHTML()) parent.skippedEntity(name); 
  }

  // I could track namespace declarations with these next two
  // methods and only pass along those for XHTML. However,
  // that is quite tricky because the endPrefixMapping()
  // method only reports the prefix, not the URI. 
  public void startPrefixMapping(String prefix, String uri)
   throws SAXException {
    parent.startPrefixMapping(prefix, uri);     
  }
  
  public void endPrefixMapping(String prefix) 
   throws SAXException {
    parent.endPrefixMapping(prefix); 
  }

  // Methods that pass data along unchanged:
  public void startDocument() throws SAXException {
    namespaces = new Stack();
    parent.startDocument(); 
  }

  public void endDocument() throws SAXException {
    parent.endDocument(); 
  }
    
  public void setDocumentLocator(Locator locator) {
    parent.setDocumentLocator(locator); 
  }
  
}
