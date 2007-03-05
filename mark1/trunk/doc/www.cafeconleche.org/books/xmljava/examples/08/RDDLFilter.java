import org.xml.sax.*;
import java.io.IOException;


public class RDDLFilter implements XMLFilter {

  private XMLReader parent;
  
  public void setParent(XMLReader parent) {
    this.parent = parent;
  }
  
  public XMLReader getParent() {
    return this.parent; 
  }

  public void setContentHandler(ContentHandler handler) {
    parent.setContentHandler(new RDDLStripper(handler));
  }
  
  // Transparent pass-along methods
  public ContentHandler getContentHandler() {
    return parent.getContentHandler();
  }  
  
  public boolean getFeature(String name)
   throws SAXNotRecognizedException, SAXNotSupportedException {
    return parent.getFeature(name);
  } 

  public void setFeature(String name, boolean value) 
   throws SAXNotRecognizedException, SAXNotSupportedException { 
    parent.setFeature(name, value);
  }

  public Object getProperty(String name) 
   throws SAXNotRecognizedException, SAXNotSupportedException {
    return parent.getProperty(name);
  }

  public void setProperty(String name, Object value)
   throws SAXNotRecognizedException, SAXNotSupportedException {
    parent.setProperty(name, value);
  }

  public void setEntityResolver(EntityResolver resolver) {
    parent.setEntityResolver(resolver);
  }
  
  public EntityResolver getEntityResolver() {
    return parent.getEntityResolver();
  }
  
  public void setDTDHandler(DTDHandler handler) {
    parent.setDTDHandler(handler);
  }
  
  public DTDHandler getDTDHandler() {
    return parent.getDTDHandler();
  }

  public void setErrorHandler(ErrorHandler handler) {
    parent.setErrorHandler(handler);
  }
  
  public ErrorHandler getErrorHandler() {
    return parent.getErrorHandler();
  }

  public void parse(InputSource input)
   throws SAXException, IOException {
    parent.parse(input);
  }
  
  public void parse(String systemId) 
   throws SAXException, IOException {
    parent.parse(systemId);
  } 
  
}
