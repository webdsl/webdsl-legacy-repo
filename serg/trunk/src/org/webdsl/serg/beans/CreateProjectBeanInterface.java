package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue3(Issue issue21);

  public void addIssue3(Issue issue21);

  public void removePerson17(Person person171);

  public void addPerson17(Person person171);

  public String cancel();

  public String save();

  public void setNewIssue22(String p);

  public String getNewIssue22();

  public void selectIssue22(ValueChangeEvent event);

  public Map<String, String> getIssue22List();

  public void initIssue22List();

  public void setNewPerson172(String p);

  public String getNewPerson172();

  public void selectPerson172(ValueChangeEvent event);

  public Map<String, String> getPerson172List();

  public void initPerson172List();

  public List<Person> getPerson97List();

  public void initPerson97List();

  public List<ResearchProject> getProject93List();

  public void initProject93List();

  public Project getProject();

  public void setProject(Project project);
}