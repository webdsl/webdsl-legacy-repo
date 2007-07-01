package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson1(Person person185);

  public void addPerson1(Person person185);

  public void addNewAuthor();

  public void removeResearchProject5(ResearchProject researchProject27);

  public void addResearchProject5(ResearchProject researchProject27);

  public String cancel();

  public String save();

  public void setNewPerson186(String p);

  public String getNewPerson186();

  public void selectPerson186(ValueChangeEvent event);

  public Map<String, String> getPerson186List();

  public void initPerson186List();

  public void setNewResearchProject28(String p);

  public String getNewResearchProject28();

  public void selectResearchProject28(ValueChangeEvent event);

  public Map<String, String> getResearchProject28List();

  public void initResearchProject28List();

  public List<Person> getPerson67List();

  public void initPerson67List();

  public List<ResearchProject> getProject54List();

  public void initProject54List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}