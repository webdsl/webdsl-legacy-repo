package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue3(Issue issue25);

  public void addIssue3(Issue issue25);

  public void removePerson17(Person person193);

  public void addPerson17(Person person193);

  public String cancel();

  public String save();

  public void setNewIssue26(String p);

  public String getNewIssue26();

  public void selectIssue26(ValueChangeEvent event);

  public Map<String, String> getIssue26List();

  public void initIssue26List();

  public void setNewPerson194(String p);

  public String getNewPerson194();

  public void selectPerson194(ValueChangeEvent event);

  public Map<String, String> getPerson194List();

  public void initPerson194List();

  public List<Person> getPerson104List();

  public void initPerson104List();

  public List<ResearchProject> getProject93List();

  public void initProject93List();

  public Project getProject();

  public void setProject(Project project);
}