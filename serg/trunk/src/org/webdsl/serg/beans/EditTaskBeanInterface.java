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

  public void removeIssue7(Issue issue32);

  public void addIssue7(Issue issue32);

  public void removePerson25(Person person203);

  public void addPerson25(Person person203);

  public String cancel();

  public String save();

  public void setNewIssue33(String p);

  public String getNewIssue33();

  public void selectIssue33(ValueChangeEvent event);

  public Map<String, String> getIssue33List();

  public void initIssue33List();

  public void setNewPerson204(String p);

  public String getNewPerson204();

  public void selectPerson204(ValueChangeEvent event);

  public Map<String, String> getPerson204List();

  public void initPerson204List();

  public List<Person> getPerson103List();

  public void initPerson103List();

  public List<ResearchProject> getProject103List();

  public void initProject103List();
}