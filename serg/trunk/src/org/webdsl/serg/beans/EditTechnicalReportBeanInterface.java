package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public TechnicalReport getTechnicalReport();

  public void removePerson2(Person person153);

  public void addPerson2(Person person153);

  public void addNewAuthor();

  public void removeResearchProject6(ResearchProject researchProject19);

  public void addResearchProject6(ResearchProject researchProject19);

  public void setPublication0(Publication publication16);

  public String cancel();

  public String save();

  public void setNewPerson154(String p);

  public String getNewPerson154();

  public void selectPerson154(ValueChangeEvent event);

  public Map<String, String> getPerson154List();

  public void initPerson154List();

  public void setNewResearchProject20(String p);

  public String getNewResearchProject20();

  public void selectResearchProject20(ValueChangeEvent event);

  public Map<String, String> getResearchProject20List();

  public void initResearchProject20List();

  public void setNewPublication15(String p);

  public String getNewPublication15();

  public void selectPublication15(ValueChangeEvent event);

  public Map<String, String> getPublication15List();

  public void initPublication15List();

  public List<Person> getPerson62List();

  public void initPerson62List();

  public List<ResearchProject> getProject57List();

  public void initProject57List();

  public Person getNewAuthor2();

  public void setNewAuthor2(Person newAuthor2);
}