package org.jdom;

public class CDATA extends Text {

  protected CDATA() { }
  public    CDATA(String s) throws IllegalDataException;
  
  public Text setText(String s) throws IllegalDataException;
  public void append(String s) throws IllegalDataException;

  public String toString();
  
}
