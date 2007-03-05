import org.xml.sax.*;


public class OpaqueFilter implements XMLFilter {

  private XMLReader parent;
  
  public void setParent(XMLReader parent) {
    this.parent = parent;
  }
  
  public XMLReader getParent() {
    return this.parent; 
  }

  public boolean getFeature(String name)
   throws SAXNotRecognizedException {
    throw new SAXNotRecognizedException(name);
  } 

  public void setFeature(String name, boolean value) 
   throws SAXNotRecognizedException { 
    throw new SAXNotRecognizedException(name);
  }

  public Object getProperty(String name) 
   throws SAXNotRecognizedException {
    throw new SAXNotRecognizedException(name);
  }


  public void setProperty(String name, Object value)
   throws SAXNotRecognizedException {
    throw new SAXNotRecognizedException(name);
  }

  public void setEntityResolver(EntityResolver resolver) {}
  public EntityResolver getEntityResolver() {
    return null; 
  }
  
  public void setDTDHandler(DTDHandler handler) {}
  public DTDHandler getDTDHandler() {
    return null; 
  }

  public void setContentHandler(ContentHandler handler) {}
  public ContentHandler getContentHandler() {
    return null; 
  }

  public void setErrorHandler(ErrorHandler handler) {}
  public ErrorHandler getErrorHandler() {
    return null; 
  }

  public void parse(InputSource input) {}
  public void parse(String systemID) {} 
  
}
