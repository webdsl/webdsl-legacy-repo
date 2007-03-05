package org.jdom;

public class Comment implements Serializable, Cloneable {

  protected String text;
  protected Object parent;

  protected Comment();
  public Comment(String text);
  
  public Element    getParent(); 
  protected Comment setParent(Element parent);
  public Comment    detach();
  public Document   getDocument();
  protected Comment setDocument(Document document);
  public String     getText();
  public Comment    setText(String text);

  public       String  toString();
  public final boolean equals(Object ob);
  public final int     hashCode();
  public       Object  clone();
  
}
