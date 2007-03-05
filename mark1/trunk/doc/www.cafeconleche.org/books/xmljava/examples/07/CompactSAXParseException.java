package org.xml.sax;

public class SAXParseException extends SAXException {
    
  public SAXParseException(String message, Locator locator)
  public SAXParseException(String message, Locator locator,
	 Exception e) 
  public SAXParseException(String message, String publicID, 
   String systemID, int lineNumber, int columnNumber)
  public SAXParseException(String message, String publicID, 
   String systemID, int lineNumber, int columnNumber, 
   Exception e)
   
  public String getPublicId()
  public String getSystemId()
  public int    getLineNumber()
  public int    getColumnNumber()
    
}
