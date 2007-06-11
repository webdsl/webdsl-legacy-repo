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

  public void removePerson3(Person person49);

  public void addPerson3(Person person49);

  public void addNewAuthor();

  public void removeResearchProject9(ResearchProject researchProject26);

  public void addResearchProject9(ResearchProject researchProject26);

  public void setPublication0(Publication publication8);

  public String cancel();

  public String save();

  public void setNewPerson50(String p);

  public String getNewPerson50();

  public void selectPerson50(ValueChangeEvent event);

  public Map<String, String> getPerson50List();

  public void initPerson50List();

  public void setNewResearchProject27(String p);

  public String getNewResearchProject27();

  public void selectResearchProject27(ValueChangeEvent event);

  public Map<String, String> getResearchProject27List();

  public void initResearchProject27List();

  public void setNewPublication7(String p);

  public String getNewPublication7();

  public void selectPublication7(ValueChangeEvent event);

  public Map<String, String> getPublication7List();

  public void initPublication7List();

  public List<Person> getPerson1037List();

  public void initPerson1037List();

  public List<ResearchProject> getProject1137List();

  public void initProject1137List();

  public Person getNewAuthor2();

  public void setNewAuthor2(Person newAuthor2);
}