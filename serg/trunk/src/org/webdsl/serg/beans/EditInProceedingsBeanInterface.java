package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditInProceedingsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setInProceedings(InProceedings inProceedings);

  public InProceedings getInProceedings();

  public void removePerson6(Person person198);

  public void addPerson6(Person person198);

  public void addNewAuthor();

  public void removeResearchProject10(ResearchProject researchProject42);

  public void addResearchProject10(ResearchProject researchProject42);

  public void setConference0(Conference conference7);

  public String cancel();

  public String save();

  public void setNewPerson199(String p);

  public String getNewPerson199();

  public void selectPerson199(ValueChangeEvent event);

  public Map<String, String> getPerson199List();

  public void initPerson199List();

  public void setNewResearchProject43(String p);

  public String getNewResearchProject43();

  public void selectResearchProject43(ValueChangeEvent event);

  public Map<String, String> getResearchProject43List();

  public void initResearchProject43List();

  public void setNewConference6(String p);

  public String getNewConference6();

  public void selectConference6(ValueChangeEvent event);

  public Map<String, String> getConference6List();

  public void initConference6List();

  public List<Person> getPerson78List();

  public void initPerson78List();

  public List<ResearchProject> getProject65List();

  public void initProject65List();

  public Person getNewAuthor4();

  public void setNewAuthor4(Person newAuthor4);
}