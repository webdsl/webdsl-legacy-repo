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

  public void removeIssue2(Issue issue23);

  public void addIssue2(Issue issue23);

  public void removePerson16(Person person191);

  public void addPerson16(Person person191);

  public String cancel();

  public String save();

  public void setNewIssue24(String p);

  public String getNewIssue24();

  public void selectIssue24(ValueChangeEvent event);

  public Map<String, String> getIssue24List();

  public void initIssue24List();

  public void setNewPerson192(String p);

  public String getNewPerson192();

  public void selectPerson192(ValueChangeEvent event);

  public Map<String, String> getPerson192List();

  public void initPerson192List();

  public List<Person> getPerson103List();

  public void initPerson103List();

  public List<ResearchProject> getProject91List();

  public void initProject91List();
}