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

  public void removePerson4(Person person158);

  public void addPerson4(Person person158);

  public void addNewAuthor();

  public void removeResearchProject8(ResearchProject researchProject24);

  public void addResearchProject8(ResearchProject researchProject24);

  public void setConference0(Conference conference6);

  public String cancel();

  public String save();

  public void setNewPerson159(String p);

  public String getNewPerson159();

  public void selectPerson159(ValueChangeEvent event);

  public Map<String, String> getPerson159List();

  public void initPerson159List();

  public void setNewResearchProject25(String p);

  public String getNewResearchProject25();

  public void selectResearchProject25(ValueChangeEvent event);

  public Map<String, String> getResearchProject25List();

  public void initResearchProject25List();

  public void setNewConference5(String p);

  public String getNewConference5();

  public void selectConference5(ValueChangeEvent event);

  public Map<String, String> getConference5List();

  public void initConference5List();

  public List<Person> getPerson66List();

  public void initPerson66List();

  public List<ResearchProject> getProject61List();

  public void initProject61List();

  public Person getNewAuthor4();

  public void setNewAuthor4(Person newAuthor4);
}