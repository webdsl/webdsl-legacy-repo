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

  public void removePerson21(Person person221);

  public void addPerson21(Person person221);

  public String cancel();

  public String save();

  public void setNewIssue37(String p);

  public String getNewIssue37();

  public void selectIssue37(ValueChangeEvent event);

  public Map<String, String> getIssue37List();

  public void initIssue37List();

  public void setNewPerson222(String p);

  public String getNewPerson222();

  public void selectPerson222(ValueChangeEvent event);

  public Map<String, String> getPerson222List();

  public void initPerson222List();

  public List<Person> getPerson113List();

  public void initPerson113List();

  public List<ResearchProject> getProject106List();

  public void initProject106List();

  public Bug getBug();

  public void setBug(Bug bug);
}