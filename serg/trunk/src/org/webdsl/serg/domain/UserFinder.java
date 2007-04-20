package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

@Local public interface UserFinder 
{ 
  public void initialize();

  public void findEntries();

  public void refresh();

  public void delete();

  public void destroy();
}