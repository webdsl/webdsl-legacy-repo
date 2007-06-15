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

  public void removeIssue4(Issue issue24);

  public void addIssue4(Issue issue24);

  public void removePerson18(Person person192);

  public void addPerson18(Person person192);

  public String cancel();

  public String save();

  public void setNewIssue25(String p);

  public String getNewIssue25();

  public void selectIssue25(ValueChangeEvent event);

  public Map<String, String> getIssue25List();

  public void initIssue25List();

  public void setNewPerson193(String p);

  public String getNewPerson193();

  public void selectPerson193(ValueChangeEvent event);

  public Map<String, String> getPerson193List();

  public void initPerson193List();

  public List<Person> getPerson101List();

  public void initPerson101List();

  public List<ResearchProject> getProject101List();

  public void initProject101List();
}