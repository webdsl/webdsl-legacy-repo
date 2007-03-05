package org.xml.sax;

public interface DTDHandler {

  public void notationDecl(String name, String publicID, 
   String systemID) throws SAXException;
   
  public void unparsedEntityDecl(String name, String publicID, 
   String systemID, String notationName) throws SAXException;
    
}
