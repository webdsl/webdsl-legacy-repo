package org.xml.sax;


public interface Locator {
        
  public String getPublicId();
  public String getSystemId();
  public int    getLineNumber();
  public int    getColumnNumber();
    
}
