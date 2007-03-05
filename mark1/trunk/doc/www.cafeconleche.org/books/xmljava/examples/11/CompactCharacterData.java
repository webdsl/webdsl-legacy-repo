package org.w3c.dom;

public interface CharacterData extends Node {
  
  public String getData() throws DOMException;
  public void   setData(String data) throws DOMException;
  public int    getLength();
  public String substringData(int offset, int length)
   throws DOMException;
  public void   appendData(String data) throws DOMException;
  public void   insertData(int offset, String data)
   throws DOMException;
  public void   deleteData(int offset, int length)
   throws DOMException;
  public void   replaceData(int offset, int length, String data)
   throws DOMException;

}
