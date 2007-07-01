package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeBug(Bug bug4);

  public List<Person> getPerson117List();

  public void initPerson117List();

  public List<ResearchProject> getProject108List();

  public void initProject108List();

  public List<Bug> getBug3List();

  public void initBug3List();
}