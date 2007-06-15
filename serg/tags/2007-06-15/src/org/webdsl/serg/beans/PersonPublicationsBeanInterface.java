package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface PersonPublicationsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson(Person person);

  public Person getPerson();

  public List<Person> getPerson17List();

  public void initPerson17List();

  public List<ResearchProject> getProject8List();

  public void initProject8List();

  public java.util.List<ResearchProject> getProjects4();

  public void setProjects4(java.util.List<ResearchProject> projects4);

  public void initProjects4();

  public java.util.List<Publication> getOrderedPublications1();

  public void setOrderedPublications1(java.util.List<Publication> orderedPublications1);

  public void initOrderedPublications1();
}