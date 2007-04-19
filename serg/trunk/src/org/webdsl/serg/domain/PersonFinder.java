package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

@Local public interface PersonFinder 
{ 
  public void findEntries();

  public void delete();

  public void destroy();
}