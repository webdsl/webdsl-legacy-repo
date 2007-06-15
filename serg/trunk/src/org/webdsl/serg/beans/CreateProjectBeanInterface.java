package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue3(Issue issue31);

  public void addIssue3(Issue issue31);

  public void removePerson17(Person person194);

  public void addPerson17(Person person194);

  public String cancel();

  public String save();

  public void setNewIssue32(String p);

  public String getNewIssue32();

  public void selectIssue32(ValueChangeEvent event);

  public Map<String, String> getIssue32List();

  public void initIssue32List();

  public void setNewPerson195(String p);

  public String getNewPerson195();

  public void selectPerson195(ValueChangeEvent event);

  public Map<String, String> getPerson195List();

  public void initPerson195List();

  public List<Person> getPerson103List();

  public void initPerson103List();

  public List<ResearchProject> getProject94List();

  public void initProject94List();

  public Project getProject();

  public void setProject(Project project);
}