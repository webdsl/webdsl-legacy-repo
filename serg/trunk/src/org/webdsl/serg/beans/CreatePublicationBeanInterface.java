package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson1(Person person166);

  public void addPerson1(Person person166);

  public void addNewAuthor();

  public void removeResearchProject5(ResearchProject researchProject26);

  public void addResearchProject5(ResearchProject researchProject26);

  public String cancel();

  public String save();

  public void setNewPerson167(String p);

  public String getNewPerson167();

  public void selectPerson167(ValueChangeEvent event);

  public Map<String, String> getPerson167List();

  public void initPerson167List();

  public void setNewResearchProject27(String p);

  public String getNewResearchProject27();

  public void selectResearchProject27(ValueChangeEvent event);

  public Map<String, String> getResearchProject27List();

  public void initResearchProject27List();

  public List<Person> getPerson64List();

  public void initPerson64List();

  public List<ResearchProject> getProject54List();

  public void initProject54List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}