package org.jdom;

public class DocType implements Serializable, Cloneable {

  protected String   elementName;
  protected String   publicID;
  protected String   systemID;
  protected Document document;
  protected String   internalSubset;

  protected DocType();
  public DocType(String elementName, String publicID, 
   String systemID);
  public DocType(String elementName, String systemID);
  public DocType(String elementName);

  public String   getElementName();
  public DocType  setElementName(String elementName);
  public String   getPublicID();
  public DocType  setPublicID(String publicID);
  public String   getSystemID();
  public DocType  setSystemID(String systemID);
  public Document getDocument();
  public void     setInternalSubset(String newData);

  public String getInternalSubset();

  public       String  toString();
  public final boolean equals(Object o);
  public final int     hashCode();
  public       Object  clone();

}
