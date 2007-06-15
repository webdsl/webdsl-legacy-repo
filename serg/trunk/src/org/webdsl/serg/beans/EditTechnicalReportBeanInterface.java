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

  public void removePerson2(Person person157);

  public void addPerson2(Person person157);

  public void addNewAuthor();

  public void removeResearchProject6(ResearchProject researchProject24);

  public void addResearchProject6(ResearchProject researchProject24);

  public void setPublication0(Publication publication17);

  public String cancel();

  public String save();

  public void setNewPerson158(String p);

  public String getNewPerson158();

  public void selectPerson158(ValueChangeEvent event);

  public Map<String, String> getPerson158List();

  public void initPerson158List();

  public void setNewResearchProject25(String p);

  public String getNewResearchProject25();

  public void selectResearchProject25(ValueChangeEvent event);

  public Map<String, String> getResearchProject25List();

  public void initResearchProject25List();

  public void setNewPublication16(String p);

  public String getNewPublication16();

  public void selectPublication16(ValueChangeEvent event);

  public Map<String, String> getPublication16List();

  public void initPublication16List();

  public List<Person> getPerson62List();

  public void initPerson62List();

  public List<ResearchProject> getProject56List();

  public void initProject56List();

  public Person getNewAuthor2();

  public void setNewAuthor2(Person newAuthor2);
}