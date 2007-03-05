import org.xml.sax.*;
import org.xml.sax.helpers.XMLFilterImpl;
import java.util.*;


public class MultiHandlerAdapter extends XMLFilterImpl {

  public MultiHandlerAdapter(XMLReader parent) {
    super(parent);
  }

  List contentHandlers = new ArrayList(2);
  List dtdHandlers = new ArrayList(2);
  List errorHandlers = new ArrayList(2);
  
  public void setContentHandler(ContentHandler handler) {
    
    if (handler == null) {
      contentHandlers.clear(); 
    }
    contentHandlers.add(handler); 
    
  }

  // There's no good way to handle this within the XMLReader
  // interface. I just pick the first in the list. 
  public ContentHandler getContentHandler() {
    if (contentHandlers.isEmpty()) return null;
    return (ContentHandler) contentHandlers.get(0);
  }
  
  public void setDTDHandler(DTDHandler handler) {
    
    if (handler == null) {
      dtdHandlers.clear(); 
    }
    dtdHandlers.add(handler); 
    
  }

  public DTDHandler getDTDHandler() {
    if (dtdHandlers.isEmpty()) return null;
    return (DTDHandler) dtdHandlers.get(0);
  }
  
  public void setErrorHandler(ErrorHandler handler) {
    
    if (handler == null) {
      errorHandlers.clear(); 
    }
    errorHandlers.add(handler); 
    
  }

  public ErrorHandler getErrorHandler() {
    if (errorHandlers.isEmpty()) return null;
    return (ErrorHandler) errorHandlers.get(0);
  }
  
  // ContentHandler implementation
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException {    
   
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.startElement(namespaceURI, localName, 
       qualifiedName, atts);
    }
    
  }  

  public void endElement(String namespaceURI, String localName,
   String qualifiedName) throws SAXException {    
   
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.endElement(namespaceURI, localName, 
       qualifiedName);
    }
    
  }  

  public void characters(char[] text, int start, int length)
   throws SAXException {
   
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.characters(text, start, length);
    }
    
  }
  
  public void ignorableWhitespace(char[] text, int start, 
   int length) throws SAXException {
   
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.ignorableWhitespace(text, start, length); 
    }
  }
  
  public void processingInstruction(String target, String data)
   throws SAXException {
   
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.processingInstruction(target, data); 
    }
  }
  
  public void skippedEntity(String name)
   throws SAXException {
   
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.skippedEntity(name); 
    }
  }

  public void startPrefixMapping(String prefix, String uri)
   throws SAXException {
   
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.startPrefixMapping(prefix, uri); 
    }
    
  }
  
  public void endPrefixMapping(String prefix) 
   throws SAXException {
     
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.endPrefixMapping(prefix); 
    }
    
  }

  public void startDocument() throws SAXException {

    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.startDocument();
    }
    
  }

  public void endDocument() throws SAXException {
    
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.endDocument();
    }
    
  }
    
  public void setDocumentLocator(Locator locator) {
    
    Iterator handlers = contentHandlers.iterator();
    while(handlers.hasNext()) {
      ContentHandler handler = (ContentHandler) handlers.next();
      handler.setDocumentLocator(locator); 
    }
    
  }  

  // DTDHandler implementation
  public void notationDecl(String name, String publicID, 
   String systemID) throws SAXException {
     
    Iterator handlers = dtdHandlers.iterator();
    while(handlers.hasNext()) {
      DTDHandler handler = (DTDHandler) handlers.next();
      handler.notationDecl(name, publicID, systemID); 
    }
    
  }
   
  public void unparsedEntityDecl(String name, String publicID, 
   String systemID, String notationName) throws SAXException {
     
    Iterator handlers = dtdHandlers.iterator();
    while(handlers.hasNext()) {
      DTDHandler handler = (DTDHandler) handlers.next();
      handler.unparsedEntityDecl(name, publicID, systemID, 
       notationName); 
    }
      
  }
  
  // ErrorHandler implementation
  public void warning(SAXParseException exception)
   throws SAXException {
     
    Iterator handlers = errorHandlers.iterator();
    while(handlers.hasNext()) {
      ErrorHandler handler = (ErrorHandler) handlers.next();
      handler.warning(exception); 
    }
          
  }
  
  public void error(SAXParseException exception)
   throws SAXException {
     
    Iterator handlers = errorHandlers.iterator();
    while(handlers.hasNext()) {
      ErrorHandler handler = (ErrorHandler) handlers.next();
      handler.error(exception); 
    }
          
  }
  
  public void fatalError(SAXParseException exception)
   throws SAXException {
     
    Iterator handlers = errorHandlers.iterator();
    while(handlers.hasNext()) {
      ErrorHandler handler = (ErrorHandler) handlers.next();
      handler.fatalError(exception); 
    }
          
  }
  
}
