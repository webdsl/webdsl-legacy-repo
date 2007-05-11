package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

@Local public interface PublicationEditor 
{ 
  public Publication getPublication();

  public void setPublication(Publication publication);

  public void initialize();

  public boolean isNew();

  public String create();

  public String edit();

  public String save();

  public String delete();

  public String done();

  public String view();

  public void destroy();
}