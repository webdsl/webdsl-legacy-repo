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

  public void removePerson22(Person person224);

  public void addPerson22(Person person224);

  public String cancel();

  public String save();

  public void setNewIssue40(String p);

  public String getNewIssue40();

  public void selectIssue40(ValueChangeEvent event);

  public Map<String, String> getIssue40List();

  public void initIssue40List();

  public void setNewPerson225(String p);

  public String getNewPerson225();

  public void selectPerson225(ValueChangeEvent event);

  public Map<String, String> getPerson225List();

  public void initPerson225List();

  public List<Person> getPerson116List();

  public void initPerson116List();

  public List<ResearchProject> getProject109List();

  public void initProject109List();
}