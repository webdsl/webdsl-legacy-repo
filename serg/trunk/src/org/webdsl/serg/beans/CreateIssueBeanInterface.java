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

  public void removePerson17(Person person211);

  public void addPerson17(Person person211);

  public String cancel();

  public String save();

  public void setNewIssue27(String p);

  public String getNewIssue27();

  public void selectIssue27(ValueChangeEvent event);

  public Map<String, String> getIssue27List();

  public void initIssue27List();

  public void setNewPerson212(String p);

  public String getNewPerson212();

  public void selectPerson212(ValueChangeEvent event);

  public Map<String, String> getPerson212List();

  public void initPerson212List();

  public List<Person> getPerson104List();

  public void initPerson104List();

  public List<ResearchProject> getProject93List();

  public void initProject93List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}