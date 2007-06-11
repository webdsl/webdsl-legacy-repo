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

  public void removeIssue7(Issue issue30);

  public void addIssue7(Issue issue30);

  public void removePerson18(Person person87);

  public void addPerson18(Person person87);

  public String cancel();

  public String save();

  public void setNewIssue31(String p);

  public String getNewIssue31();

  public void selectIssue31(ValueChangeEvent event);

  public Map<String, String> getIssue31List();

  public void initIssue31List();

  public void setNewPerson88(String p);

  public String getNewPerson88();

  public void selectPerson88(ValueChangeEvent event);

  public Map<String, String> getPerson88List();

  public void initPerson88List();

  public List<Person> getPerson1057List();

  public void initPerson1057List();

  public List<ResearchProject> getProject1157List();

  public void initProject1157List();
}