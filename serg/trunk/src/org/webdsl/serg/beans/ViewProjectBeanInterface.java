package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProject(Project project);

  public Project getProject();

  public String createNewIssue(Issue issue12, java.util.Set<Issue> issues1);

  public String createNewPerson(Issue issue21, java.util.Set<Person> assigned1);

  public List<Person> getPerson104List();

  public void initPerson104List();

  public List<ResearchProject> getProject96List();

  public void initProject96List();
}