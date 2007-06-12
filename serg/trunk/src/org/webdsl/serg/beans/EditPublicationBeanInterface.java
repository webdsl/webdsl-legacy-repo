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

  public void removePerson1(Person person141);

  public void addPerson1(Person person141);

  public void addNewAuthor();

  public void removeResearchProject7(ResearchProject researchProject22);

  public void addResearchProject7(ResearchProject researchProject22);

  public String cancel();

  public String save();

  public void setNewPerson142(String p);

  public String getNewPerson142();

  public void selectPerson142(ValueChangeEvent event);

  public Map<String, String> getPerson142List();

  public void initPerson142List();

  public void setNewResearchProject23(String p);

  public String getNewResearchProject23();

  public void selectResearchProject23(ValueChangeEvent event);

  public Map<String, String> getResearchProject23List();

  public void initResearchProject23List();

  public List<Person> getPerson56List();

  public void initPerson56List();

  public List<ResearchProject> getProject51List();

  public void initProject51List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}