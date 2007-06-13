package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson3(Person person137);

  public void addPerson3(Person person137);

  public void addNewAuthor();

  public void removeResearchProject7(ResearchProject researchProject21);

  public void addResearchProject7(ResearchProject researchProject21);

  public void setPublication1(Publication publication18);

  public String cancel();

  public String save();

  public void setNewPerson138(String p);

  public String getNewPerson138();

  public void selectPerson138(ValueChangeEvent event);

  public Map<String, String> getPerson138List();

  public void initPerson138List();

  public void setNewResearchProject22(String p);

  public String getNewResearchProject22();

  public void selectResearchProject22(ValueChangeEvent event);

  public Map<String, String> getResearchProject22List();

  public void initResearchProject22List();

  public void setNewPublication17(String p);

  public String getNewPublication17();

  public void selectPublication17(ValueChangeEvent event);

  public Map<String, String> getPublication17List();

  public void initPublication17List();

  public List<Person> getPerson62List();

  public void initPerson62List();

  public List<ResearchProject> getProject57List();

  public void initProject57List();

  public TechnicalReport getTechnicalReport();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public Person getNewAuthor3();

  public void setNewAuthor3(Person newAuthor3);
}