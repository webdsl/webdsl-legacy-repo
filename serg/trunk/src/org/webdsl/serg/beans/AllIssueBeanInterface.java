package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue(Issue issue4);

  public List<Person> getPerson96List();

  public void initPerson96List();

  public List<ResearchProject> getProject91List();

  public void initProject91List();

  public List<Issue> getIssue3List();

  public void initIssue3List();
}