package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTaskBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue7(Issue issue31);

  public void addIssue7(Issue issue31);

  public void removePerson21(Person person199);

  public void addPerson21(Person person199);

  public String cancel();

  public String save();

  public void setNewIssue32(String p);

  public String getNewIssue32();

  public void selectIssue32(ValueChangeEvent event);

  public Map<String, String> getIssue32List();

  public void initIssue32List();

  public void setNewPerson200(String p);

  public String getNewPerson200();

  public void selectPerson200(ValueChangeEvent event);

  public Map<String, String> getPerson200List();

  public void initPerson200List();

  public List<Person> getPerson106List();

  public void initPerson106List();

  public List<ResearchProject> getProject106List();

  public void initProject106List();

  public Task getTask();

  public void setTask(Task task);
}