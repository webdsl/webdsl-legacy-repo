package org.w3c.dom.ls;

public interface DOMImplementationLS {

  public static final short MODE_SYNCHRONOUS  = 1;
  public static final short MODE_ASYNCHRONOUS = 2;

  public DOMWriter      createDOMWriter();
  public DOMInputSource createDOMInputSource();
  public DOMBuilder     createDOMBuilder(short mode, 
   String schemaType) throws DOMException;

}
