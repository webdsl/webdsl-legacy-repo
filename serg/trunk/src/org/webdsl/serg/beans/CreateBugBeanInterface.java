package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue5(Issue issue30);

  public void addIssue5(Issue issue30);

  public void removePerson19(Person person198);

  public void addPerson19(Person person198);

  public String cancel();

  public String save();

  public void setNewIssue31(String p);

  public String getNewIssue31();

  public void selectIssue31(ValueChangeEvent event);

  public Map<String, String> getIssue31List();

  public void initIssue31List();

  public void setNewPerson199(String p);

  public String getNewPerson199();

  public void selectPerson199(ValueChangeEvent event);

  public Map<String, String> getPerson199List();

  public void initPerson199List();

  public List<Person> getPerson109List();

  public void initPerson109List();

  public List<ResearchProject> getProject101List();

  public void initProject101List();

  public Bug getBug();

  public void setBug(Bug bug);
}