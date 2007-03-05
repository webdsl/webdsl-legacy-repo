package org.jdom;

public class Document implements Serializable, Cloneable {

  protected ContentList content;
  protected DocType     docType;

  public Document()
  public Document(Element root, DocType docType)
  public Document(Element root) 
  public Document(List newContent, DocType docType) 
  public Document(List content)

  public boolean  hasRootElement()
  public Element  getRootElement()
  public Document setRootElement(Element rootElement)
  public Element  detachRootElement() 
  
  public DocType  getDocType()
  public Document setDocType(DocType docType)
  
  public Document addContent(ProcessingInstruction pi)
  public Document addContent(Comment comment) 
  public List     getContent()
  public List     getContent(Filter filter)
  public Document setContent(List newContent)
  public boolean  removeContent(ProcessingInstruction pi)
  public boolean  removeContent(Comment comment) 
  
  // Java utility methods
  public       String  toString()
  public final boolean equals(Object o)
  public final int     hashCode()
  public       Object  clone() 
  
}
