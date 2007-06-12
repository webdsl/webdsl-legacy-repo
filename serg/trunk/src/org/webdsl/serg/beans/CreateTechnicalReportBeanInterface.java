package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson4(Person person149);

  public void addPerson4(Person person149);

  public void addNewAuthor();

  public void removeResearchProject10(ResearchProject researchProject30);

  public void addResearchProject10(ResearchProject researchProject30);

  public void setPublication1(Publication publication18);

  public String cancel();

  public String save();

  public void setNewPerson150(String p);

  public String getNewPerson150();

  public void selectPerson150(ValueChangeEvent event);

  public Map<String, String> getPerson150List();

  public void initPerson150List();

  public void setNewResearchProject31(String p);

  public String getNewResearchProject31();

  public void selectResearchProject31(ValueChangeEvent event);

  public Map<String, String> getResearchProject31List();

  public void initResearchProject31List();

  public void setNewPublication17(String p);

  public String getNewPublication17();

  public void selectPublication17(ValueChangeEvent event);

  public Map<String, String> getPublication17List();

  public void initPublication17List();

  public List<Person> getPerson61List();

  public void initPerson61List();

  public List<ResearchProject> getProject56List();

  public void initProject56List();

  public TechnicalReport getTechnicalReport();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public Person getNewAuthor3();

  public void setNewAuthor3(Person newAuthor3);
}