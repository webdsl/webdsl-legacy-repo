package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson3(Person person171);

  public void addPerson3(Person person171);

  public void addNewAuthor();

  public void removeResearchProject7(ResearchProject researchProject32);

  public void addResearchProject7(ResearchProject researchProject32);

  public void setPublication1(Publication publication25);

  public String cancel();

  public String save();

  public void setNewPerson172(String p);

  public String getNewPerson172();

  public void selectPerson172(ValueChangeEvent event);

  public Map<String, String> getPerson172List();

  public void initPerson172List();

  public void setNewResearchProject33(String p);

  public String getNewResearchProject33();

  public void selectResearchProject33(ValueChangeEvent event);

  public Map<String, String> getResearchProject33List();

  public void initResearchProject33List();

  public void setNewPublication24(String p);

  public String getNewPublication24();

  public void selectPublication24(ValueChangeEvent event);

  public Map<String, String> getPublication24List();

  public void initPublication24List();

  public List<Person> getPerson68List();

  public void initPerson68List();

  public List<ResearchProject> getProject58List();

  public void initProject58List();

  public TechnicalReport getTechnicalReport();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public Person getNewAuthor3();

  public void setNewAuthor3(Person newAuthor3);
}