package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateInProceedingsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson7(Person person186);

  public void addPerson7(Person person186);

  public void addNewAuthor();

  public void removeResearchProject11(ResearchProject researchProject44);

  public void addResearchProject11(ResearchProject researchProject44);

  public void setConference1(Conference conference9);

  public String cancel();

  public String save();

  public void setNewPerson187(String p);

  public String getNewPerson187();

  public void selectPerson187(ValueChangeEvent event);

  public Map<String, String> getPerson187List();

  public void initPerson187List();

  public void setNewResearchProject45(String p);

  public String getNewResearchProject45();

  public void selectResearchProject45(ValueChangeEvent event);

  public Map<String, String> getResearchProject45List();

  public void initResearchProject45List();

  public void setNewConference8(String p);

  public String getNewConference8();

  public void selectConference8(ValueChangeEvent event);

  public Map<String, String> getConference8List();

  public void initConference8List();

  public List<Person> getPerson77List();

  public void initPerson77List();

  public List<ResearchProject> getProject66List();

  public void initProject66List();

  public InProceedings getInProceedings();

  public void setInProceedings(InProceedings inProceedings);

  public Person getNewAuthor5();

  public void setNewAuthor5(Person newAuthor5);
}