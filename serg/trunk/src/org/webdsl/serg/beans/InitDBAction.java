package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;

@Local public interface InitDBAction 
{ 
  public String initDB();

  public void destroy();
}