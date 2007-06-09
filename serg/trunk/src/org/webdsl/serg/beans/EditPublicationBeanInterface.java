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

  public void removePerson1(Person person36);

  public void addPerson1(Person person36);

  public void addNewAuthor();

  public void removeResearchProject1(ResearchProject researchProject5);

  public void addResearchProject1(ResearchProject researchProject5);

  public String cancel();

  public String save();

  public void setNewPerson37(String p);

  public String getNewPerson37();

  public void selectPerson37(ValueChangeEvent event);

  public Map<String, String> getPerson37List();

  public void initPerson37List();

  public void setNewResearchProject6(String p);

  public String getNewResearchProject6();

  public void selectResearchProject6(ValueChangeEvent event);

  public Map<String, String> getResearchProject6List();

  public void initResearchProject6List();

  public List<Person> getPerson1033List();

  public void initPerson1033List();

  public List<ResearchProject> getProject1133List();

  public void initProject1133List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}