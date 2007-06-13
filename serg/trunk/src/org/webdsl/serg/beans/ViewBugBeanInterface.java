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

  public List<Person> getPerson102List();

  public void initPerson102List();

  public List<ResearchProject> getProject102List();

  public void initProject102List();
}