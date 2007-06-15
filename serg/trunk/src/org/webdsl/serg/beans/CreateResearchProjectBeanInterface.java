package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson11(Person person179);

  public void addPerson11(Person person179);

  public void setPublication3(Publication publication25);

  public void removePublication1(Publication publication26);

  public void addPublication1(Publication publication26);

  public String cancel();

  public String save();

  public void setNewPerson180(String p);

  public String getNewPerson180();

  public void selectPerson180(ValueChangeEvent event);

  public Map<String, String> getPerson180List();

  public void initPerson180List();

  public void setNewPublication24(String p);

  public String getNewPublication24();

  public void selectPublication24(ValueChangeEvent event);

  public Map<String, String> getPublication24List();

  public void initPublication24List();

  public void setNewPublication27(String p);

  public String getNewPublication27();

  public void selectPublication27(ValueChangeEvent event);

  public Map<String, String> getPublication27List();

  public void initPublication27List();

  public List<Person> getPerson87List();

  public void initPerson87List();

  public List<ResearchProject> getProject77List();

  public void initProject77List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}