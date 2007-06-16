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

  public void removePerson19(Person person211);

  public void addPerson19(Person person211);

  public String cancel();

  public String save();

  public void setNewIssue37(String p);

  public String getNewIssue37();

  public void selectIssue37(ValueChangeEvent event);

  public Map<String, String> getIssue37List();

  public void initIssue37List();

  public void setNewPerson212(String p);

  public String getNewPerson212();

  public void selectPerson212(ValueChangeEvent event);

  public Map<String, String> getPerson212List();

  public void initPerson212List();

  public List<Person> getPerson107List();

  public void initPerson107List();

  public List<ResearchProject> getProject102List();

  public void initProject102List();

  public Bug getBug();

  public void setBug(Bug bug);
}