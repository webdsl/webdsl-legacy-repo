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

  public void removePerson0(Person person169);

  public void addPerson0(Person person169);

  public void addNewAuthor();

  public void removeResearchProject4(ResearchProject researchProject25);

  public void addResearchProject4(ResearchProject researchProject25);

  public String cancel();

  public String save();

  public void setNewPerson170(String p);

  public String getNewPerson170();

  public void selectPerson170(ValueChangeEvent event);

  public Map<String, String> getPerson170List();

  public void initPerson170List();

  public void setNewResearchProject26(String p);

  public String getNewResearchProject26();

  public void selectResearchProject26(ValueChangeEvent event);

  public Map<String, String> getResearchProject26List();

  public void initResearchProject26List();

  public List<Person> getPerson64List();

  public void initPerson64List();

  public List<ResearchProject> getProject53List();

  public void initProject53List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}