package org.xml.sax;


public interface Attributes {

  public int    getLength ();
  
  public String getQName(int index);
  public String getURI(int index);
  public String getLocalName(int index);
  public int    getIndex(String uri, String localPart);
  public int    getIndex(String qualifiedName);
  public String getType(String uri, String localName);
  public String getType(String qualifiedName);
  public String getType(int index);
  public String getValue(String uri, String localName);
  public String getValue(String qualifiedName);
  public String getValue(int index);

}
