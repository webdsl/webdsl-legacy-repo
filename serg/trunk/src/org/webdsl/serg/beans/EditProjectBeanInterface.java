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

  public void removePerson16(Person person187);

  public void addPerson16(Person person187);

  public String cancel();

  public String save();

  public void setNewIssue20(String p);

  public String getNewIssue20();

  public void selectIssue20(ValueChangeEvent event);

  public Map<String, String> getIssue20List();

  public void initIssue20List();

  public void setNewPerson188(String p);

  public String getNewPerson188();

  public void selectPerson188(ValueChangeEvent event);

  public Map<String, String> getPerson188List();

  public void initPerson188List();

  public List<Person> getPerson97List();

  public void initPerson97List();

  public List<ResearchProject> getProject92List();

  public void initProject92List();
}