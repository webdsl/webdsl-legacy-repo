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

  public void removePerson23(Person person226);

  public void addPerson23(Person person226);

  public String cancel();

  public String save();

  public void setNewIssue42(String p);

  public String getNewIssue42();

  public void selectIssue42(ValueChangeEvent event);

  public Map<String, String> getIssue42List();

  public void initIssue42List();

  public void setNewPerson227(String p);

  public String getNewPerson227();

  public void selectPerson227(ValueChangeEvent event);

  public Map<String, String> getPerson227List();

  public void initPerson227List();

  public List<Person> getPerson117List();

  public void initPerson117List();

  public List<ResearchProject> getProject110List();

  public void initProject110List();

  public Task getTask();

  public void setTask(Task task);
}