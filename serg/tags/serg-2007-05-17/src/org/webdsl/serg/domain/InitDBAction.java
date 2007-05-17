package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

@Local public interface InitDBAction 
{ 
  public String initDB();

  public void destroy();
}