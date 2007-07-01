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

  public void removeIssue6(Issue issue39);

  public void addIssue6(Issue issue39);

  public void removePerson22(Person person238);

  public void addPerson22(Person person238);

  public String cancel();

  public String save();

  public void setNewIssue40(String p);

  public String getNewIssue40();

  public void selectIssue40(ValueChangeEvent event);

  public Map<String, String> getIssue40List();

  public void initIssue40List();

  public void setNewPerson239(String p);

  public String getNewPerson239();

  public void selectPerson239(ValueChangeEvent event);

  public Map<String, String> getPerson239List();

  public void initPerson239List();

  public List<Person> getPerson118List();

  public void initPerson118List();

  public List<ResearchProject> getProject109List();

  public void initProject109List();
}