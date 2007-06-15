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

  public void removePerson20(Person person202);

  public void addPerson20(Person person202);

  public String cancel();

  public String save();

  public void setNewIssue40(String p);

  public String getNewIssue40();

  public void selectIssue40(ValueChangeEvent event);

  public Map<String, String> getIssue40List();

  public void initIssue40List();

  public void setNewPerson203(String p);

  public String getNewPerson203();

  public void selectPerson203(ValueChangeEvent event);

  public Map<String, String> getPerson203List();

  public void initPerson203List();

  public List<Person> getPerson110List();

  public void initPerson110List();

  public List<ResearchProject> getProject105List();

  public void initProject105List();
}