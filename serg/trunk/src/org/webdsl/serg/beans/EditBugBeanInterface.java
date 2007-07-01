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

  public void removeIssue4(Issue issue34);

  public void addIssue4(Issue issue34);

  public void removePerson20(Person person233);

  public void addPerson20(Person person233);

  public String cancel();

  public String save();

  public void setNewIssue35(String p);

  public String getNewIssue35();

  public void selectIssue35(ValueChangeEvent event);

  public Map<String, String> getIssue35List();

  public void initIssue35List();

  public void setNewPerson234(String p);

  public String getNewPerson234();

  public void selectPerson234(ValueChangeEvent event);

  public Map<String, String> getPerson234List();

  public void initPerson234List();

  public List<Person> getPerson114List();

  public void initPerson114List();

  public List<ResearchProject> getProject105List();

  public void initProject105List();
}