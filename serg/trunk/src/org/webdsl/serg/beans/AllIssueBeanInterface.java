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

  public List<Person> getPerson106List();

  public void initPerson106List();

  public List<ResearchProject> getProject95List();

  public void initProject95List();

  public List<Issue> getIssue6List();

  public void initIssue6List();
}