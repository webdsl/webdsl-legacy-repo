package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setTask(Task task);

  public Task getTask();

  public String createNewIssue(Issue issue110, java.util.Set<Issue> issues3);

  public String createNewPerson(Issue issue23, java.util.Set<Person> assigned3);

  public List<Person> getPerson120List();

  public void initPerson120List();

  public List<ResearchProject> getProject111List();

  public void initProject111List();
}