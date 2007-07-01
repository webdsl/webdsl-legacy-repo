package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue5(Issue issue36);

  public void addIssue5(Issue issue36);

  public void removePerson21(Person person235);

  public void addPerson21(Person person235);

  public String cancel();

  public String save();

  public void setNewIssue37(String p);

  public String getNewIssue37();

  public void selectIssue37(ValueChangeEvent event);

  public Map<String, String> getIssue37List();

  public void initIssue37List();

  public void setNewPerson236(String p);

  public String getNewPerson236();

  public void selectPerson236(ValueChangeEvent event);

  public Map<String, String> getPerson236List();

  public void initPerson236List();

  public List<Person> getPerson115List();

  public void initPerson115List();

  public List<ResearchProject> getProject106List();

  public void initProject106List();

  public Bug getBug();

  public void setBug(Bug bug);
}