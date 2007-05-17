package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import org.webdsl.serg.domain.*;

@Local public interface PersonPublicationsBeanInterface 
{ 
  public void setP(Person p);

  public Person getP();

  public void initialize();

  public void destroy();
}