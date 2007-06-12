package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue8(Issue issue34);

  public void addIssue8(Issue issue34);

  public void removePerson26(Person person205);

  public void addPerson26(Person person205);

  public String cancel();

  public String save();

  public void setNewIssue35(String p);

  public String getNewIssue35();

  public void selectIssue35(ValueChangeEvent event);

  public Map<String, String> getIssue35List();

  public void initIssue35List();

  public void setNewPerson206(String p);

  public String getNewPerson206();

  public void selectPerson206(ValueChangeEvent event);

  public Map<String, String> getPerson206List();

  public void initPerson206List();

  public List<Person> getPerson104List();

  public void initPerson104List();

  public List<ResearchProject> getProject104List();

  public void initProject104List();

  public Task getTask();

  public void setTask(Task task);
}