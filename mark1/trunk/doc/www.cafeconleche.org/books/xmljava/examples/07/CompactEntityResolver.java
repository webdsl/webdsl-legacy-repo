package org.xml.sax;

public interface EntityResolver {

  public InputSource resolveEntity(String publicId, 
   String systemId) throws SAXException, IOException;
    
}
