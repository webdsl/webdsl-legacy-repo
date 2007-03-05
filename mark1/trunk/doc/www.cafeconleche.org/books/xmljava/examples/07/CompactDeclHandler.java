package org.xml.sax.ext;

public interface DeclHandler {

  public void elementDecl(String name, String model)
   throws SAXException;
  public void attributeDecl(String elementName, 
   String attributeName, String type, String mode, 
   String defaultValue) throws SAXException;
  public void internalEntityDecl(String name, String value) 
   throws SAXException;
  public void externalEntityDecl(String name, String publicID, 
   String systemID) throws SAXException;

}
