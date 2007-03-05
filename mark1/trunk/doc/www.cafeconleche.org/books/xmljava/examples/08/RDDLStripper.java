import org.xml.sax.*;


public class RDDLStripper implements ContentHandler {

  public final static String RDDL_NAMESPACE 
   = "http://www.rddl.org/";
  public final static String XHTML_NAMESPACE 
   = "http://www.w3.org/1999/xhtml";
  
  private ContentHandler parent;
  
  public RDDLStripper(ContentHandler parent) {
    this.parent = parent;
  }

  // Filter out <rddl:resource> start-tags 
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException {
     
    if (localName.equals("resource") 
        && namespaceURI.equals(RDDL_NAMESPACE)) {
      return; // having done nothing
    }
    else { // pass the element along
      parent.startElement(namespaceURI, localName, qualifiedName, 
       atts);
    }
    
  }
  
  // Filter out </rddl:resource> end-tags 
  public void endElement(String namespaceURI, String localName,
   String qualifiedName) throws SAXException {
     
    if (localName.equals("resource") 
        && namespaceURI.equals(RDDL_NAMESPACE)) {
      return; // having done nothing
    }
    else {
      parent.endElement(namespaceURI, localName, qualifiedName);
    }
    
  }
  
  // Methods that pass data along unchanged:
  public void startDocument() throws SAXException {
    parent.startDocument(); 
  }
  
  public void startPrefixMapping(String prefix, String uri)
   throws SAXException {
    parent.startPrefixMapping(prefix, uri); 
  }
  
  public void endPrefixMapping(String prefix) 
   throws SAXException {
    parent.endPrefixMapping(prefix); 
  }

  public void setDocumentLocator(Locator locator) {
    parent.setDocumentLocator(locator); 
  }
  
  public void endDocument() throws SAXException {
    parent.endDocument(); 
  }
  
  public void characters(char[] text, int start, int length)
   throws SAXException {
    parent.characters(text, start, length); 
  }
  
  public void ignorableWhitespace(char[] text, int start, 
   int length) throws SAXException {
    parent.ignorableWhitespace(text, start, length); 
  }
  
  public void processingInstruction(String target, String data)
   throws SAXException {
    parent.processingInstruction(target, data); 
  }
  
  public void skippedEntity(String name)
   throws SAXException {
    parent.skippedEntity(name); 
  }

}
