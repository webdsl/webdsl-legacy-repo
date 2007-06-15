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

  public void removePerson0(Person person153);

  public void addPerson0(Person person153);

  public void addNewAuthor();

  public void removeResearchProject4(ResearchProject researchProject24);

  public void addResearchProject4(ResearchProject researchProject24);

  public String cancel();

  public String save();

  public void setNewPerson154(String p);

  public String getNewPerson154();

  public void selectPerson154(ValueChangeEvent event);

  public Map<String, String> getPerson154List();

  public void initPerson154List();

  public void setNewResearchProject25(String p);

  public String getNewResearchProject25();

  public void selectResearchProject25(ValueChangeEvent event);

  public Map<String, String> getResearchProject25List();

  public void initResearchProject25List();

  public List<Person> getPerson63List();

  public void initPerson63List();

  public List<ResearchProject> getProject53List();

  public void initProject53List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}