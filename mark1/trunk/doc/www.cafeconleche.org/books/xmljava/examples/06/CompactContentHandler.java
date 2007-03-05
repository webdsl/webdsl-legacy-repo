package org.xml.sax;


public interface ContentHandler {

  public void setDocumentLocator(Locator locator);
  public void startDocument() throws SAXException;
  public void endDocument() throws SAXException;
  public void startPrefixMapping(String prefix, String uri)
   throws SAXException;
  public void endPrefixMapping(String prefix) 
   throws SAXException;
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException;
  public void endElement(String namespaceURI, String localName,
   String qualifiedName) throws SAXException;
  public void characters(char[] text, int start, int length)
   throws SAXException;
  public void ignorableWhitespace(char[] text, int start, 
   int length) throws SAXException;
  public void processingInstruction(String target, String data)
   throws SAXException;
  public void skippedEntity(String name)
   throws SAXException;

}
