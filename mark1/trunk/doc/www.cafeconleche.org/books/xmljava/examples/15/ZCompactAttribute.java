package org.jdom;

public class Attribute implements Serializable, Cloneable {

  public final static int UNDECLARED_ATTRIBUTE = 0;
  public final static int CDATA_ATTRIBUTE      = 1;
  public final static int ID_ATTRIBUTE         = 2;
  public final static int IDREF_ATTRIBUTE      = 3;
  public final static int IDREFS_ATTRIBUTE     = 4;
  public final static int ENTITY_ATTRIBUTE     = 5;
  public final static int ENTITIES_ATTRIBUTE   = 6;
  public final static int NMTOKEN_ATTRIBUTE    = 7;
  public final static int NMTOKENS_ATTRIBUTE   = 8;
  public final static int NOTATION_ATTRIBUTE   = 9;
  public final static int ENUMERATED_ATTRIBUTE = 10;

  protected           String    name;
  protected transient Namespace namespace;
  protected           String    value;
  protected           int       type;
  protected           Object    parent;

  protected Attribute();
  
  public Attribute(String name, String value, 
   Namespace namespace);
  public Attribute(String name, String value, int type, 
   Namespace namespace);
  public Attribute(String name, String value);
  public Attribute(String name, String value, int type);

  public Document     getDocument();
  public Element      getParent();
  protected Attribute setParent(Element parent);
  public Attribute    detach();
  public String       getName();
  public Attribute    setName(String name);
  public String       getQualifiedName();
  public String       getNamespacePrefix();
  public String       getNamespaceURI();
  public Namespace    getNamespace();
  public Attribute    setNamespace(Namespace namespace);
  public String       getValue();
  public Attribute    setValue(String value);
  public int          getAttributeType();
  public Attribute    setAttributeType(int type);
  
  public String        toString();
  public final boolean equals(Object o);
  public final int     hashCode();
  public Object        clone();

  public int     getIntValue() throws DataConversionException;
  public long    getLongValue() throws DataConversionException;
  public float   getFloatValue() throws DataConversionException;
  public double  getDoubleValue() throws DataConversionException;
  public boolean getBooleanValue() throws DataConversionException;

}
