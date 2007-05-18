package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import org.webdsl.serg.domain.*;

@Local public interface ViewPersonBeanInterface 
{ 
  public void setPerson(Person person);

  public Person getPerson();

  public void initialize();

  public void destroy();
}