package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setIssue(Issue issue);

  public Issue getIssue();

  public String createNewIssue(Issue issue10, java.util.Set<Issue> issues0);

  public String createNewPerson(Issue issue20, java.util.Set<Person> assigned0);

  public List<Person> getPerson105List();

  public void initPerson105List();

  public List<ResearchProject> getProject94List();

  public void initProject94List();
}