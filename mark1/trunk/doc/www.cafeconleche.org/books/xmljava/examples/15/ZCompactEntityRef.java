package org.jdom;

public class EntityRef implements Serializable, Cloneable {

  protected String name;
  protected String publicID;
  protected String systemID;
  protected Object parent;

  protected EntityRef();

  public EntityRef(String name);
  public EntityRef(String name, String systemID);
  public EntityRef(String name, String publicID, 
   String systemID);

  public EntityRef detach();   
   
  public Document  getDocument();
  public String    getName();
  public EntityRef setName(String name);
  public Element   getParent();
  public String    getPublicID();
  public EntityRef setPublicID(String newPublicID);
  public String    getSystemID();
  public EntityRef setSystemID(String newSystemID);

  public final boolean equals(Object ob);  
  public final int     hashCode();  
  public       String  toString();
  public       Object  clone();

}
