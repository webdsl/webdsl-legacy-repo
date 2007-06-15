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

  public void removeIssue6(Issue issue33);

  public void addIssue6(Issue issue33);

  public void removePerson20(Person person201);

  public void addPerson20(Person person201);

  public String cancel();

  public String save();

  public void setNewIssue34(String p);

  public String getNewIssue34();

  public void selectIssue34(ValueChangeEvent event);

  public Map<String, String> getIssue34List();

  public void initIssue34List();

  public void setNewPerson202(String p);

  public String getNewPerson202();

  public void selectPerson202(ValueChangeEvent event);

  public Map<String, String> getPerson202List();

  public void initPerson202List();

  public List<Person> getPerson113List();

  public void initPerson113List();

  public List<ResearchProject> getProject104List();

  public void initProject104List();
}