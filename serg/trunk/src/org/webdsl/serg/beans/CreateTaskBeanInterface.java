package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue8(Issue issue32);

  public void addIssue8(Issue issue32);

  public void removePerson19(Person person89);

  public void addPerson19(Person person89);

  public String cancel();

  public String save();

  public void setNewIssue33(String p);

  public String getNewIssue33();

  public void selectIssue33(ValueChangeEvent event);

  public Map<String, String> getIssue33List();

  public void initIssue33List();

  public void setNewPerson90(String p);

  public String getNewPerson90();

  public void selectPerson90(ValueChangeEvent event);

  public Map<String, String> getPerson90List();

  public void initPerson90List();

  public List<Person> getPerson1058List();

  public void initPerson1058List();

  public List<ResearchProject> getProject1158List();

  public void initProject1158List();

  public Task getTask();

  public void setTask(Task task);
}