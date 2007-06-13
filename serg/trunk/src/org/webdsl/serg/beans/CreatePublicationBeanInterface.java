package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson1(Person person132);

  public void addPerson1(Person person132);

  public void addNewAuthor();

  public void removeResearchProject5(ResearchProject researchProject16);

  public void addResearchProject5(ResearchProject researchProject16);

  public String cancel();

  public String save();

  public void setNewPerson133(String p);

  public String getNewPerson133();

  public void selectPerson133(ValueChangeEvent event);

  public Map<String, String> getPerson133List();

  public void initPerson133List();

  public void setNewResearchProject17(String p);

  public String getNewResearchProject17();

  public void selectResearchProject17(ValueChangeEvent event);

  public Map<String, String> getResearchProject17List();

  public void initResearchProject17List();

  public List<Person> getPerson58List();

  public void initPerson58List();

  public List<ResearchProject> getProject53List();

  public void initProject53List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}