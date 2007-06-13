package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProject(Project project);

  public Project getProject();

  public void removeIssue2(Issue issue19);

  public void addIssue2(Issue issue19);

  public void removePerson16(Person person169);

  public void addPerson16(Person person169);

  public String cancel();

  public String save();

  public void setNewIssue20(String p);

  public String getNewIssue20();

  public void selectIssue20(ValueChangeEvent event);

  public Map<String, String> getIssue20List();

  public void initIssue20List();

  public void setNewPerson170(String p);

  public String getNewPerson170();

  public void selectPerson170(ValueChangeEvent event);

  public Map<String, String> getPerson170List();

  public void initPerson170List();

  public List<Person> getPerson96List();

  public void initPerson96List();

  public List<ResearchProject> getProject91List();

  public void initProject91List();
}