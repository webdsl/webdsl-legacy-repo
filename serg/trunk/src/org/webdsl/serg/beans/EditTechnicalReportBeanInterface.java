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

  public void removePerson4(Person person193);

  public void addPerson4(Person person193);

  public void addNewAuthor();

  public void removeResearchProject8(ResearchProject researchProject36);

  public void addResearchProject8(ResearchProject researchProject36);

  public void setPublication0(Publication publication23);

  public String cancel();

  public String save();

  public void setNewPerson194(String p);

  public String getNewPerson194();

  public void selectPerson194(ValueChangeEvent event);

  public Map<String, String> getPerson194List();

  public void initPerson194List();

  public void setNewResearchProject37(String p);

  public String getNewResearchProject37();

  public void selectResearchProject37(ValueChangeEvent event);

  public Map<String, String> getResearchProject37List();

  public void initResearchProject37List();

  public void setNewPublication22(String p);

  public String getNewPublication22();

  public void selectPublication22(ValueChangeEvent event);

  public Map<String, String> getPublication22List();

  public void initPublication22List();

  public List<Person> getPerson74List();

  public void initPerson74List();

  public List<ResearchProject> getProject61List();

  public void initProject61List();

  public Person getNewAuthor2();

  public void setNewAuthor2(Person newAuthor2);
}