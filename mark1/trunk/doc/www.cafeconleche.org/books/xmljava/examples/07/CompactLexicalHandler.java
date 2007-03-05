package org.xml.sax.ext;

public interface LexicalHandler {

  public void startDTD(String name, String publicId, 
   String systemId) throws SAXException;
  public void endDTD() throws SAXException;

  public void startEntity(String name)
   throws SAXException;
  public void endEntity(String name) throws SAXException;

  public void startCDATA() throws SAXException;
  public void endCDATA() throws SAXException;

  public void comment(char[] text, int start, int length)
   throws SAXException;

}
