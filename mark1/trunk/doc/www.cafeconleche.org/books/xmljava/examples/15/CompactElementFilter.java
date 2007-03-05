package org.jdom.filter;

public class ElementFilter implements Filter {

  protected String    name;
  protected Namespace namespace;
  
  public ElementFilter();
  public ElementFilter(String name);
  public ElementFilter(Namespace namespace);
  public ElementFilter(String name, Namespace namespace);

  public boolean canAdd(Object o);
  public boolean canRemove(Object o);
  public boolean matches(Object o);

  public boolean equals(Object o);
  
}
