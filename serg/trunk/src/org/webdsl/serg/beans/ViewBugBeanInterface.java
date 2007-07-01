package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBug(Bug bug);

  public Bug getBug();

  public String createNewIssue(Issue issue16, java.util.Set<Issue> issues2);

  public String createNewPerson(Issue issue22, java.util.Set<Person> assigned2);

  public List<Person> getPerson116List();

  public void initPerson116List();

  public List<ResearchProject> getProject107List();

  public void initProject107List();
}