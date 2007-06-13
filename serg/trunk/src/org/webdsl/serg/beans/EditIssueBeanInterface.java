package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setIssue(Issue issue);

  public Issue getIssue();

  public void removeIssue0(Issue issue14);

  public void addIssue0(Issue issue14);

  public void removePerson14(Person person164);

  public void addPerson14(Person person164);

  public String cancel();

  public String save();

  public void setNewIssue15(String p);

  public String getNewIssue15();

  public void selectIssue15(ValueChangeEvent event);

  public Map<String, String> getIssue15List();

  public void initIssue15List();

  public void setNewPerson165(String p);

  public String getNewPerson165();

  public void selectPerson165(ValueChangeEvent event);

  public Map<String, String> getPerson165List();

  public void initPerson165List();

  public List<Person> getPerson92List();

  public void initPerson92List();

  public List<ResearchProject> getProject87List();

  public void initProject87List();
}