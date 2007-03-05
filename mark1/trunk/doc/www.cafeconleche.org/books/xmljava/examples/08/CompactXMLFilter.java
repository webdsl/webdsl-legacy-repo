package org.xml.sax;

public interface XMLFilter extends XMLReader {

  public void      setParent(XMLReader parent);
  public XMLReader getParent();

}
