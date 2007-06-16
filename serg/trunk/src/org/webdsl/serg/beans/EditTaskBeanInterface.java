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

  public void removePerson20(Person person214);

  public void addPerson20(Person person214);

  public String cancel();

  public String save();

  public void setNewIssue40(String p);

  public String getNewIssue40();

  public void selectIssue40(ValueChangeEvent event);

  public Map<String, String> getIssue40List();

  public void initIssue40List();

  public void setNewPerson215(String p);

  public String getNewPerson215();

  public void selectPerson215(ValueChangeEvent event);

  public Map<String, String> getPerson215List();

  public void initPerson215List();

  public List<Person> getPerson111List();

  public void initPerson111List();

  public List<ResearchProject> getProject105List();

  public void initProject105List();
}