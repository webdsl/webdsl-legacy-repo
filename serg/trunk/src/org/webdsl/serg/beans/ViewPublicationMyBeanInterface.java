package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPublicationMyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPub(Publication pub);

  public Publication getPub();

  public String deletePublication(Publication pub);
}