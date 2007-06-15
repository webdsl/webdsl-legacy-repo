package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue7(Issue issue35);

  public void addIssue7(Issue issue35);

  public void removePerson21(Person person203);

  public void addPerson21(Person person203);

  public String cancel();

  public String save();

  public void setNewIssue36(String p);

  public String getNewIssue36();

  public void selectIssue36(ValueChangeEvent event);

  public Map<String, String> getIssue36List();

  public void initIssue36List();

  public void setNewPerson204(String p);

  public String getNewPerson204();

  public void selectPerson204(ValueChangeEvent event);

  public Map<String, String> getPerson204List();

  public void initPerson204List();

  public List<Person> getPerson114List();

  public void initPerson114List();

  public List<ResearchProject> getProject105List();

  public void initProject105List();

  public Task getTask();

  public void setTask(Task task);
}