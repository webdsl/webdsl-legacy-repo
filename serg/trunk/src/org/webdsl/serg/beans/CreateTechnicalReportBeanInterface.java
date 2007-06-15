package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson3(Person person159);

  public void addPerson3(Person person159);

  public void addNewAuthor();

  public void removeResearchProject7(ResearchProject researchProject26);

  public void addResearchProject7(ResearchProject researchProject26);

  public void setPublication1(Publication publication19);

  public String cancel();

  public String save();

  public void setNewPerson160(String p);

  public String getNewPerson160();

  public void selectPerson160(ValueChangeEvent event);

  public Map<String, String> getPerson160List();

  public void initPerson160List();

  public void setNewResearchProject27(String p);

  public String getNewResearchProject27();

  public void selectResearchProject27(ValueChangeEvent event);

  public Map<String, String> getResearchProject27List();

  public void initResearchProject27List();

  public void setNewPublication18(String p);

  public String getNewPublication18();

  public void selectPublication18(ValueChangeEvent event);

  public Map<String, String> getPublication18List();

  public void initPublication18List();

  public List<Person> getPerson63List();

  public void initPerson63List();

  public List<ResearchProject> getProject57List();

  public void initProject57List();

  public TechnicalReport getTechnicalReport();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public Person getNewAuthor3();

  public void setNewAuthor3(Person newAuthor3);
}