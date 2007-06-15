package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue5(Issue issue26);

  public void addIssue5(Issue issue26);

  public void removePerson19(Person person194);

  public void addPerson19(Person person194);

  public String cancel();

  public String save();

  public void setNewIssue27(String p);

  public String getNewIssue27();

  public void selectIssue27(ValueChangeEvent event);

  public Map<String, String> getIssue27List();

  public void initIssue27List();

  public void setNewPerson195(String p);

  public String getNewPerson195();

  public void selectPerson195(ValueChangeEvent event);

  public Map<String, String> getPerson195List();

  public void initPerson195List();

  public List<Person> getPerson102List();

  public void initPerson102List();

  public List<ResearchProject> getProject102List();

  public void initProject102List();

  public Bug getBug();

  public void setBug(Bug bug);
}