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

  public void removeIssue2(Issue issue29);

  public void addIssue2(Issue issue29);

  public void removePerson16(Person person203);

  public void addPerson16(Person person203);

  public String cancel();

  public String save();

  public void setNewIssue30(String p);

  public String getNewIssue30();

  public void selectIssue30(ValueChangeEvent event);

  public Map<String, String> getIssue30List();

  public void initIssue30List();

  public void setNewPerson204(String p);

  public String getNewPerson204();

  public void selectPerson204(ValueChangeEvent event);

  public Map<String, String> getPerson204List();

  public void initPerson204List();

  public List<Person> getPerson102List();

  public void initPerson102List();

  public List<ResearchProject> getProject92List();

  public void initProject92List();
}