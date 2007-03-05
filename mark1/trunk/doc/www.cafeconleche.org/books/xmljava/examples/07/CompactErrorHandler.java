package org.xml.sax;

public interface ErrorHandler {

  public void warning(SAXParseException exception)
   throws SAXException;
  public void error(SAXParseException exception)
   throws SAXException;
  public void fatalError(SAXParseException exception)
   throws SAXException;
    
}
