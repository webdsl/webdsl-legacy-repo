package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson(Person person);

  public Person getPerson();

  public List<Person> getPerson2List();

  public void initPerson2List();

  public List<ResearchProject> getProject1List();

  public void initProject1List();

  public java.util.List<ResearchProject> getProjects0();

  public void setProjects0(java.util.List<ResearchProject> projects0);

  public void initProjects0();

  public java.util.List<Publication> getOrderedPublications0();

  public void setOrderedPublications0(java.util.List<Publication> orderedPublications0);

  public void initOrderedPublications0();

  public java.util.List<ResearchProject> getProjects1();

  public void setProjects1(java.util.List<ResearchProject> projects1);

  public void initProjects1();
}