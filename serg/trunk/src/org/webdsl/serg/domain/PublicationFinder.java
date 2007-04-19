package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

@Local public interface PublicationFinder 
{ 
  public void findEntries();

  public void delete();

  public void destroy();
}