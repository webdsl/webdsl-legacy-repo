package org.xml.sax.helpers;

public class AttributesImpl implements Attributes {

  public AttributesImpl();
  public AttributesImpl(Attributes atts);
  
  public int      getLength();
  public String getURI(int index);
  public String getLocalName(int index);
  public String getQualifiedName(int index);
  public String getType(int index);
  public String getValue(int index);
  public int      getIndex(String uri, String localName);
  public int      getIndex(String qualifiedName);
  public String getType(String uri, String localName);
  public String getType(String qualifiedName);
  public String getValue(String uri, String localName);
  public String getValue(String qualifiedName);
  
  public void clear();
  public void setAttributes(Attributes atts);
  public void addAttribute(String uri, String localName, 
   String qualifiedName, String type, String value);
  public void setAttribute(int index, String uri, 
   String localName, String qualifiedName, String type, 
   String value);
  public void removeAttribute(int index);
  public void setURI(int index, String uri);
  public void setLocalName(int index, String localName);
  public void setQualifiedName(int index, String qualifiedName);
  public void setType(int index, String type);
  public void setValue(int index, String value);

}
