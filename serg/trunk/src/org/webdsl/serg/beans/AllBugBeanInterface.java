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

  public List<Person> getPerson104List();

  public void initPerson104List();

  public List<ResearchProject> getProject104List();

  public void initProject104List();

  public List<Bug> getBug3List();

  public void initBug3List();
}