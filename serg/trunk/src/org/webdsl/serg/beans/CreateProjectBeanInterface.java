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

  public void removePerson17(Person person189);

  public void addPerson17(Person person189);

  public String cancel();

  public String save();

  public void setNewIssue22(String p);

  public String getNewIssue22();

  public void selectIssue22(ValueChangeEvent event);

  public Map<String, String> getIssue22List();

  public void initIssue22List();

  public void setNewPerson190(String p);

  public String getNewPerson190();

  public void selectPerson190(ValueChangeEvent event);

  public Map<String, String> getPerson190List();

  public void initPerson190List();

  public List<Person> getPerson98List();

  public void initPerson98List();

  public List<ResearchProject> getProject94List();

  public void initProject94List();

  public Project getProject();

  public void setProject(Project project);
}