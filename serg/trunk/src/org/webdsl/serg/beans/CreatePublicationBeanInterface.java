package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson2(Person person38);

  public void addPerson2(Person person38);

  public void addNewAuthor();

  public void removeResearchProject2(ResearchProject researchProject7);

  public void addResearchProject2(ResearchProject researchProject7);

  public String cancel();

  public String save();

  public void setNewPerson39(String p);

  public String getNewPerson39();

  public void selectPerson39(ValueChangeEvent event);

  public Map<String, String> getPerson39List();

  public void initPerson39List();

  public void setNewResearchProject8(String p);

  public String getNewResearchProject8();

  public void selectResearchProject8(ValueChangeEvent event);

  public Map<String, String> getResearchProject8List();

  public void initResearchProject8List();

  public List<Person> getPerson1035List();

  public void initPerson1035List();

  public List<ResearchProject> getProject1135List();

  public void initProject1135List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}