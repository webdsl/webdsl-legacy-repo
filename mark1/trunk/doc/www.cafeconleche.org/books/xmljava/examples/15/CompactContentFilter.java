package org.jdom.filter;

public class ContentFilter implements Filter {

  public static final int ELEMENT   = 1;
  public static final int CDATA     = 2;
  public static final int TEXT      = 4;
  public static final int COMMENT   = 8;
  public static final int PI        = 16;
  public static final int ENTITYREF = 32;
  public static final int DOCUMENT  = 64;

  protected int filterMask;

  public ContentFilter();
  public ContentFilter(boolean allVisible);
  public ContentFilter(int mask);
  
  public int  getFilterMask();
  public void setFilterMask(int mask);
  public void setDefaultMask();
  
  public void setDocumentContent();
  public void setElementContent();
  
  public void setElementVisible(boolean visible);
  public void setCDATAVisible(boolean visible)
  public void setTextVisible(boolean visible);
  public void setCommentVisible(boolean visible);
  public void setPIVisible(boolean visible);
  public void setEntityRefVisible(boolean visible);
  
  public boolean canAdd(Object o);
  public boolean canRemove(Object o);
  public boolean matches(Object o);

  
  public boolean equals(Object o);
  
}
