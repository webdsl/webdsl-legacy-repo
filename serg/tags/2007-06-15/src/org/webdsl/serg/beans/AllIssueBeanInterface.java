package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue(Issue issue7);

  public List<Person> getPerson101List();

  public void initPerson101List();

  public List<ResearchProject> getProject91List();

  public void initProject91List();

  public List<Issue> getIssue6List();

  public void initIssue6List();
}