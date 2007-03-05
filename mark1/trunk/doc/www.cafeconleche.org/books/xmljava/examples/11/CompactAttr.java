package org.w3c.dom;

public interface Attr extends Node {

  public String  getName();  // Prefixed name
  public String  getValue(); 
  public Element getOwnerElement();
  public boolean getSpecified();
  public void    setValue(String value) throws DOMException;

}
