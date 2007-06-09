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

  public List<ResearchProject> getPr0List();

  public void initPr0List();

  public List<Person> getPerson100List();

  public void initPerson100List();

  public List<ResearchProject> getProject110List();

  public void initProject110List();

  public java.util.List<Publication> getPublications0();

  public void setPublications0(java.util.List<Publication> publications0);

  public void initPublications0();

  public java.util.List<Publication> getOrderedPublications0();

  public void setOrderedPublications0(java.util.List<Publication> orderedPublications0);

  public void initOrderedPublications0();

  public java.util.List<ResearchProject> getProjects0();

  public void setProjects0(java.util.List<ResearchProject> projects0);

  public void initProjects0();
}