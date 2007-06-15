package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson1(Person person150);

  public void addPerson1(Person person150);

  public void addNewAuthor();

  public void removeResearchProject5(ResearchProject researchProject16);

  public void addResearchProject5(ResearchProject researchProject16);

  public String cancel();

  public String save();

  public void setNewPerson151(String p);

  public String getNewPerson151();

  public void selectPerson151(ValueChangeEvent event);

  public Map<String, String> getPerson151List();

  public void initPerson151List();

  public void setNewResearchProject17(String p);

  public String getNewResearchProject17();

  public void selectResearchProject17(ValueChangeEvent event);

  public Map<String, String> getResearchProject17List();

  public void initResearchProject17List();

  public List<Person> getPerson59List();

  public void initPerson59List();

  public List<ResearchProject> getProject54List();

  public void initProject54List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}