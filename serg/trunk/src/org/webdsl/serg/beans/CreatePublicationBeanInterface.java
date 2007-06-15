package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson1(Person person154);

  public void addPerson1(Person person154);

  public void addNewAuthor();

  public void removeResearchProject5(ResearchProject researchProject21);

  public void addResearchProject5(ResearchProject researchProject21);

  public String cancel();

  public String save();

  public void setNewPerson155(String p);

  public String getNewPerson155();

  public void selectPerson155(ValueChangeEvent event);

  public Map<String, String> getPerson155List();

  public void initPerson155List();

  public void setNewResearchProject22(String p);

  public String getNewResearchProject22();

  public void selectResearchProject22(ValueChangeEvent event);

  public Map<String, String> getResearchProject22List();

  public void initResearchProject22List();

  public List<Person> getPerson58List();

  public void initPerson58List();

  public List<ResearchProject> getProject53List();

  public void initProject53List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}