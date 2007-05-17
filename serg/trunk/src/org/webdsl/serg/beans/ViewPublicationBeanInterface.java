package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import org.webdsl.serg.domain.*;

@Local public interface ViewPublicationBeanInterface 
{ 
  public void setPub(Publication pub);

  public Publication getPub();

  public void initialize();

  public void destroy();
}