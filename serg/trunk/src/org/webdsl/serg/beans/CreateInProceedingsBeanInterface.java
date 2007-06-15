package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateInProceedingsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson5(Person person160);

  public void addPerson5(Person person160);

  public void addNewAuthor();

  public void removeResearchProject9(ResearchProject researchProject26);

  public void addResearchProject9(ResearchProject researchProject26);

  public void setConference1(Conference conference8);

  public String cancel();

  public String save();

  public void setNewPerson161(String p);

  public String getNewPerson161();

  public void selectPerson161(ValueChangeEvent event);

  public Map<String, String> getPerson161List();

  public void initPerson161List();

  public void setNewResearchProject27(String p);

  public String getNewResearchProject27();

  public void selectResearchProject27(ValueChangeEvent event);

  public Map<String, String> getResearchProject27List();

  public void initResearchProject27List();

  public void setNewConference7(String p);

  public String getNewConference7();

  public void selectConference7(ValueChangeEvent event);

  public Map<String, String> getConference7List();

  public void initConference7List();

  public List<Person> getPerson67List();

  public void initPerson67List();

  public List<ResearchProject> getProject62List();

  public void initProject62List();

  public InProceedings getInProceedings();

  public void setInProceedings(InProceedings inProceedings);

  public Person getNewAuthor5();

  public void setNewAuthor5(Person newAuthor5);
}