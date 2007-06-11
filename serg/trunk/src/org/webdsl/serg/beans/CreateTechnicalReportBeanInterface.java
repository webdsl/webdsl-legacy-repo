package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson4(Person person51);

  public void addPerson4(Person person51);

  public void addNewAuthor();

  public void removeResearchProject10(ResearchProject researchProject28);

  public void addResearchProject10(ResearchProject researchProject28);

  public void setPublication1(Publication publication10);

  public String cancel();

  public String save();

  public void setNewPerson52(String p);

  public String getNewPerson52();

  public void selectPerson52(ValueChangeEvent event);

  public Map<String, String> getPerson52List();

  public void initPerson52List();

  public void setNewResearchProject29(String p);

  public String getNewResearchProject29();

  public void selectResearchProject29(ValueChangeEvent event);

  public Map<String, String> getResearchProject29List();

  public void initResearchProject29List();

  public void setNewPublication9(String p);

  public String getNewPublication9();

  public void selectPublication9(ValueChangeEvent event);

  public Map<String, String> getPublication9List();

  public void initPublication9List();

  public List<Person> getPerson1038List();

  public void initPerson1038List();

  public List<ResearchProject> getProject1138List();

  public void initProject1138List();

  public TechnicalReport getTechnicalReport();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public Person getNewAuthor3();

  public void setNewAuthor3(Person newAuthor3);
}