package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface PersonPublicationsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setP(Person p);

  public Person getP();

  public List<ResearchProject> getPrList();

  public void initPrList();

  public List<Person> getPerson10List();

  public void initPerson10List();
}