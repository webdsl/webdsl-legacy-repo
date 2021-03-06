package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson5(Person person195);

  public void addPerson5(Person person195);

  public void addNewAuthor();

  public void removeResearchProject9(ResearchProject researchProject38);

  public void addResearchProject9(ResearchProject researchProject38);

  public void setPublication1(Publication publication25);

  public String cancel();

  public String save();

  public void setNewPerson196(String p);

  public String getNewPerson196();

  public void selectPerson196(ValueChangeEvent event);

  public Map<String, String> getPerson196List();

  public void initPerson196List();

  public void setNewResearchProject39(String p);

  public String getNewResearchProject39();

  public void selectResearchProject39(ValueChangeEvent event);

  public Map<String, String> getResearchProject39List();

  public void initResearchProject39List();

  public void setNewPublication24(String p);

  public String getNewPublication24();

  public void selectPublication24(ValueChangeEvent event);

  public Map<String, String> getPublication24List();

  public void initPublication24List();

  public List<Person> getPerson75List();

  public void initPerson75List();

  public List<ResearchProject> getProject62List();

  public void initProject62List();

  public TechnicalReport getTechnicalReport();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public Person getNewAuthor3();

  public void setNewAuthor3(Person newAuthor3);
}