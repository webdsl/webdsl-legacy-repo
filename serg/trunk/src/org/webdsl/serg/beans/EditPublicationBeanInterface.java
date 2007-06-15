package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication(Publication publication);

  public Publication getPublication();

  public void removePerson0(Person person152);

  public void addPerson0(Person person152);

  public void addNewAuthor();

  public void removeResearchProject4(ResearchProject researchProject19);

  public void addResearchProject4(ResearchProject researchProject19);

  public String cancel();

  public String save();

  public void setNewPerson153(String p);

  public String getNewPerson153();

  public void selectPerson153(ValueChangeEvent event);

  public Map<String, String> getPerson153List();

  public void initPerson153List();

  public void setNewResearchProject20(String p);

  public String getNewResearchProject20();

  public void selectResearchProject20(ValueChangeEvent event);

  public Map<String, String> getResearchProject20List();

  public void initResearchProject20List();

  public List<Person> getPerson57List();

  public void initPerson57List();

  public List<ResearchProject> getProject52List();

  public void initProject52List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}