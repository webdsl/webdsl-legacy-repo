package org.w3c.dom.ls;

public interface DOMWriter {

  public void    setFeature(String name, boolean state)
   throws DOMException;
  public boolean canSetFeature(String name, boolean state);
  public boolean getFeature(String name) throws DOMException;

  public String  getEncoding();
  public void    setEncoding(String encoding);
  public String  getNewLine();
  public void    setNewLine(String newLine);
  
  public boolean writeNode(OutputStream out, Node node)
   throws Exception;
  public String writeToString(Node node) throws DOMException;
  
  public DOMErrorHandler getErrorHandler();
  public void setErrorHandler(DOMErrorHandler errorHandler);

  public DOMWriterFilter getFilter();
  public void setFilter(DOMWriterFilter filter);

}
