package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPersonMyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson(Person person);

  public Person getPerson();

  public List<ResearchProject> getPrList();

  public void initPrList();

  public List<Person> getPerson10List();

  public void initPerson10List();

  public List<Publication> getPubList();

  public void initPubList();
}