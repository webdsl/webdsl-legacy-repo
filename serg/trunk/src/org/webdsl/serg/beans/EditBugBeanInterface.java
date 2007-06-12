package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBug(Bug bug);

  public Bug getBug();

  public void removeIssue5(Issue issue27);

  public void addIssue5(Issue issue27);

  public void removePerson23(Person person198);

  public void addPerson23(Person person198);

  public String cancel();

  public String save();

  public void setNewIssue28(String p);

  public String getNewIssue28();

  public void selectIssue28(ValueChangeEvent event);

  public Map<String, String> getIssue28List();

  public void initIssue28List();

  public void setNewPerson199(String p);

  public String getNewPerson199();

  public void selectPerson199(ValueChangeEvent event);

  public Map<String, String> getPerson199List();

  public void initPerson199List();

  public List<Person> getPerson99List();

  public void initPerson99List();

  public List<ResearchProject> getProject99List();

  public void initProject99List();
}