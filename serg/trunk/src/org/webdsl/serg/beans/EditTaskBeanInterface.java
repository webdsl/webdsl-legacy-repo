package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setTask(Task task);

  public Task getTask();

  public void removeIssue6(Issue issue29);

  public void addIssue6(Issue issue29);

  public void removePerson20(Person person179);

  public void addPerson20(Person person179);

  public String cancel();

  public String save();

  public void setNewIssue30(String p);

  public String getNewIssue30();

  public void selectIssue30(ValueChangeEvent event);

  public Map<String, String> getIssue30List();

  public void initIssue30List();

  public void setNewPerson180(String p);

  public String getNewPerson180();

  public void selectPerson180(ValueChangeEvent event);

  public Map<String, String> getPerson180List();

  public void initPerson180List();

  public List<Person> getPerson104List();

  public void initPerson104List();

  public List<ResearchProject> getProject104List();

  public void initProject104List();
}