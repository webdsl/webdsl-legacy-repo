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

  public void removePerson21(Person person181);

  public void addPerson21(Person person181);

  public String cancel();

  public String save();

  public void setNewIssue32(String p);

  public String getNewIssue32();

  public void selectIssue32(ValueChangeEvent event);

  public Map<String, String> getIssue32List();

  public void initIssue32List();

  public void setNewPerson182(String p);

  public String getNewPerson182();

  public void selectPerson182(ValueChangeEvent event);

  public Map<String, String> getPerson182List();

  public void initPerson182List();

  public List<Person> getPerson105List();

  public void initPerson105List();

  public List<ResearchProject> getProject105List();

  public void initProject105List();

  public Task getTask();

  public void setTask(Task task);
}