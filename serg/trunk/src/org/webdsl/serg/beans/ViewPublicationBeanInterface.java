package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import org.webdsl.serg.domain.*;

@Local public interface ViewPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPub(Publication pub);

  public Publication getPub();
}