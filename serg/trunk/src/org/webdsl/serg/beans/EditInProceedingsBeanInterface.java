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

  public void removePerson4(Person person174);

  public void addPerson4(Person person174);

  public void addNewAuthor();

  public void removeResearchProject8(ResearchProject researchProject35);

  public void addResearchProject8(ResearchProject researchProject35);

  public void setConference0(Conference conference7);

  public String cancel();

  public String save();

  public void setNewPerson175(String p);

  public String getNewPerson175();

  public void selectPerson175(ValueChangeEvent event);

  public Map<String, String> getPerson175List();

  public void initPerson175List();

  public void setNewResearchProject36(String p);

  public String getNewResearchProject36();

  public void selectResearchProject36(ValueChangeEvent event);

  public Map<String, String> getResearchProject36List();

  public void initResearchProject36List();

  public void setNewConference6(String p);

  public String getNewConference6();

  public void selectConference6(ValueChangeEvent event);

  public Map<String, String> getConference6List();

  public void initConference6List();

  public List<Person> getPerson71List();

  public void initPerson71List();

  public List<ResearchProject> getProject61List();

  public void initProject61List();

  public Person getNewAuthor4();

  public void setNewAuthor4(Person newAuthor4);
}