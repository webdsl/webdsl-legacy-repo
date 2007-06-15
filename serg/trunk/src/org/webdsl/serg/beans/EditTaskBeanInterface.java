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

  public void removeIssue6(Issue issue29);

  public void addIssue6(Issue issue29);

  public void removePerson20(Person person197);

  public void addPerson20(Person person197);

  public String cancel();

  public String save();

  public void setNewIssue30(String p);

  public String getNewIssue30();

  public void selectIssue30(ValueChangeEvent event);

  public Map<String, String> getIssue30List();

  public void initIssue30List();

  public void setNewPerson198(String p);

  public String getNewPerson198();

  public void selectPerson198(ValueChangeEvent event);

  public Map<String, String> getPerson198List();

  public void initPerson198List();

  public List<Person> getPerson105List();

  public void initPerson105List();

  public List<ResearchProject> getProject105List();

  public void initProject105List();
}