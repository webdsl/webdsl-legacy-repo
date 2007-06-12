package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue4(Issue issue24);

  public void addIssue4(Issue issue24);

  public void removePerson22(Person person195);

  public void addPerson22(Person person195);

  public String cancel();

  public String save();

  public void setNewIssue25(String p);

  public String getNewIssue25();

  public void selectIssue25(ValueChangeEvent event);

  public Map<String, String> getIssue25List();

  public void initIssue25List();

  public void setNewPerson196(String p);

  public String getNewPerson196();

  public void selectPerson196(ValueChangeEvent event);

  public Map<String, String> getPerson196List();

  public void initPerson196List();

  public List<Person> getPerson96List();

  public void initPerson96List();

  public List<ResearchProject> getProject92List();

  public void initProject92List();

  public Project getProject();

  public void setProject(Project project);
}