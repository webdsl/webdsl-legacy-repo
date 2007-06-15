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

  public String createNewIssue();

  public String createNewPerson();

  public List<Person> getPerson115List();

  public void initPerson115List();

  public List<ResearchProject> getProject106List();

  public void initProject106List();
}