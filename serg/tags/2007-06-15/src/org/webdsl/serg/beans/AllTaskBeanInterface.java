package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeTask(Task task4);

  public List<Person> getPerson113List();

  public void initPerson113List();

  public List<ResearchProject> getProject108List();

  public void initProject108List();

  public List<Task> getTask3List();

  public void initTask3List();
}