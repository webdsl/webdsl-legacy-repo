package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson(Person person);

  public Person getPerson();
}