package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setResearchProject(ResearchProject researchProject);

  public ResearchProject getResearchProject();

  public void removePerson10(Person person173);

  public void addPerson10(Person person173);

  public void setPublication2(Publication publication20);

  public void removePublication0(Publication publication21);

  public void addPublication0(Publication publication21);

  public String cancel();

  public String save();

  public void setNewPerson174(String p);

  public String getNewPerson174();

  public void selectPerson174(ValueChangeEvent event);

  public Map<String, String> getPerson174List();

  public void initPerson174List();

  public void setNewPublication19(String p);

  public String getNewPublication19();

  public void selectPublication19(ValueChangeEvent event);

  public Map<String, String> getPublication19List();

  public void initPublication19List();

  public void setNewPublication22(String p);

  public String getNewPublication22();

  public void selectPublication22(ValueChangeEvent event);

  public Map<String, String> getPublication22List();

  public void initPublication22List();

  public List<Person> getPerson82List();

  public void initPerson82List();

  public List<ResearchProject> getProject77List();

  public void initProject77List();
}