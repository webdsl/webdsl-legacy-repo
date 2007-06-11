package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson2(Person person45);

  public void addPerson2(Person person45);

  public void addNewAuthor();

  public void removeResearchProject8(ResearchProject researchProject22);

  public void addResearchProject8(ResearchProject researchProject22);

  public String cancel();

  public String save();

  public void setNewPerson46(String p);

  public String getNewPerson46();

  public void selectPerson46(ValueChangeEvent event);

  public Map<String, String> getPerson46List();

  public void initPerson46List();

  public void setNewResearchProject23(String p);

  public String getNewResearchProject23();

  public void selectResearchProject23(ValueChangeEvent event);

  public Map<String, String> getResearchProject23List();

  public void initResearchProject23List();

  public List<Person> getPerson1035List();

  public void initPerson1035List();

  public List<ResearchProject> getProject1135List();

  public void initProject1135List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}