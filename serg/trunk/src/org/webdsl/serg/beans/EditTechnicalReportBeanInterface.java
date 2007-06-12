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

  public void removePerson3(Person person147);

  public void addPerson3(Person person147);

  public void addNewAuthor();

  public void removeResearchProject9(ResearchProject researchProject28);

  public void addResearchProject9(ResearchProject researchProject28);

  public void setPublication0(Publication publication16);

  public String cancel();

  public String save();

  public void setNewPerson148(String p);

  public String getNewPerson148();

  public void selectPerson148(ValueChangeEvent event);

  public Map<String, String> getPerson148List();

  public void initPerson148List();

  public void setNewResearchProject29(String p);

  public String getNewResearchProject29();

  public void selectResearchProject29(ValueChangeEvent event);

  public Map<String, String> getResearchProject29List();

  public void initResearchProject29List();

  public void setNewPublication15(String p);

  public String getNewPublication15();

  public void selectPublication15(ValueChangeEvent event);

  public Map<String, String> getPublication15List();

  public void initPublication15List();

  public List<Person> getPerson60List();

  public void initPerson60List();

  public List<ResearchProject> getProject55List();

  public void initProject55List();

  public Person getNewAuthor2();

  public void setNewAuthor2(Person newAuthor2);
}