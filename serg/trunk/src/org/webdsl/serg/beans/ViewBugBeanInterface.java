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

  public String createNewIssue();

  public String createNewPerson();

  public List<Person> getPerson110List();

  public void initPerson110List();

  public List<ResearchProject> getProject102List();

  public void initProject102List();
}