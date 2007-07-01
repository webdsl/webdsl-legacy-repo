package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue1(Issue issue26);

  public void addIssue1(Issue issue26);

  public void removePerson17(Person person225);

  public void addPerson17(Person person225);

  public String cancel();

  public String save();

  public void setNewIssue27(String p);

  public String getNewIssue27();

  public void selectIssue27(ValueChangeEvent event);

  public Map<String, String> getIssue27List();

  public void initIssue27List();

  public void setNewPerson226(String p);

  public String getNewPerson226();

  public void selectPerson226(ValueChangeEvent event);

  public Map<String, String> getPerson226List();

  public void initPerson226List();

  public List<Person> getPerson106List();

  public void initPerson106List();

  public List<ResearchProject> getProject93List();

  public void initProject93List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}