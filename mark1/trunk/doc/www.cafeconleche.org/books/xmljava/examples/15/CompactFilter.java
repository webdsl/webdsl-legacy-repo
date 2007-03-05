package org.jdom.filter;

public interface Filter {

  public boolean canAdd(Object o);
  public boolean canRemove(Object o);
  public boolean matches(Object o);
    
}
