package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue7(Issue issue41);

  public void addIssue7(Issue issue41);

  public void removePerson23(Person person240);

  public void addPerson23(Person person240);

  public String cancel();

  public String save();

  public void setNewIssue42(String p);

  public String getNewIssue42();

  public void selectIssue42(ValueChangeEvent event);

  public Map<String, String> getIssue42List();

  public void initIssue42List();

  public void setNewPerson241(String p);

  public String getNewPerson241();

  public void selectPerson241(ValueChangeEvent event);

  public Map<String, String> getPerson241List();

  public void initPerson241List();

  public List<Person> getPerson119List();

  public void initPerson119List();

  public List<ResearchProject> getProject110List();

  public void initProject110List();

  public Task getTask();

  public void setTask(Task task);
}