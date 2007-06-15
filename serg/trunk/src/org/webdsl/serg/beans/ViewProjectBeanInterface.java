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

  public String createNewIssue();

  public String createNewPerson();

  public List<Person> getPerson105List();

  public void initPerson105List();

  public List<ResearchProject> getProject95List();

  public void initProject95List();
}