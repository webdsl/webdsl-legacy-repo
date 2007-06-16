package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateInProceedingsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson5(Person person175);

  public void addPerson5(Person person175);

  public void addNewAuthor();

  public void removeResearchProject9(ResearchProject researchProject37);

  public void addResearchProject9(ResearchProject researchProject37);

  public void setConference1(Conference conference9);

  public String cancel();

  public String save();

  public void setNewPerson176(String p);

  public String getNewPerson176();

  public void selectPerson176(ValueChangeEvent event);

  public Map<String, String> getPerson176List();

  public void initPerson176List();

  public void setNewResearchProject38(String p);

  public String getNewResearchProject38();

  public void selectResearchProject38(ValueChangeEvent event);

  public Map<String, String> getResearchProject38List();

  public void initResearchProject38List();

  public void setNewConference8(String p);

  public String getNewConference8();

  public void selectConference8(ValueChangeEvent event);

  public Map<String, String> getConference8List();

  public void initConference8List();

  public List<Person> getPerson72List();

  public void initPerson72List();

  public List<ResearchProject> getProject62List();

  public void initProject62List();

  public InProceedings getInProceedings();

  public void setInProceedings(InProceedings inProceedings);

  public Person getNewAuthor5();

  public void setNewAuthor5(Person newAuthor5);
}