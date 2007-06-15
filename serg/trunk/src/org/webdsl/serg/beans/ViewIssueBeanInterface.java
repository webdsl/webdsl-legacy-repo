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

  public String createNewIssue();

  public String createNewPerson();

  public List<Person> getPerson100List();

  public void initPerson100List();

  public List<ResearchProject> getProject89List();

  public void initProject89List();
}