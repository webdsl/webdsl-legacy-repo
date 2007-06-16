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

  public void removePerson2(Person person168);

  public void addPerson2(Person person168);

  public void addNewAuthor();

  public void removeResearchProject6(ResearchProject researchProject29);

  public void addResearchProject6(ResearchProject researchProject29);

  public void setPublication0(Publication publication23);

  public String cancel();

  public String save();

  public void setNewPerson169(String p);

  public String getNewPerson169();

  public void selectPerson169(ValueChangeEvent event);

  public Map<String, String> getPerson169List();

  public void initPerson169List();

  public void setNewResearchProject31(String p);

  public String getNewResearchProject31();

  public void selectResearchProject31(ValueChangeEvent event);

  public Map<String, String> getResearchProject31List();

  public void initResearchProject31List();

  public void setNewPublication22(String p);

  public String getNewPublication22();

  public void selectPublication22(ValueChangeEvent event);

  public Map<String, String> getPublication22List();

  public void initPublication22List();

  public List<Person> getPerson67List();

  public void initPerson67List();

  public List<ResearchProject> getProject57List();

  public void initProject57List();

  public Person getNewAuthor2();

  public void setNewAuthor2(Person newAuthor2);
}