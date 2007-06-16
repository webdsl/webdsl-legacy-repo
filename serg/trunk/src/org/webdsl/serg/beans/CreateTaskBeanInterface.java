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

  public void removePerson21(Person person215);

  public void addPerson21(Person person215);

  public String cancel();

  public String save();

  public void setNewIssue42(String p);

  public String getNewIssue42();

  public void selectIssue42(ValueChangeEvent event);

  public Map<String, String> getIssue42List();

  public void initIssue42List();

  public void setNewPerson216(String p);

  public String getNewPerson216();

  public void selectPerson216(ValueChangeEvent event);

  public Map<String, String> getPerson216List();

  public void initPerson216List();

  public List<Person> getPerson111List();

  public void initPerson111List();

  public List<ResearchProject> getProject106List();

  public void initProject106List();

  public Task getTask();

  public void setTask(Task task);
}