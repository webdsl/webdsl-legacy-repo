package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue5(Issue issue26);

  public void addIssue5(Issue issue26);

  public void removePerson19(Person person176);

  public void addPerson19(Person person176);

  public String cancel();

  public String save();

  public void setNewIssue27(String p);

  public String getNewIssue27();

  public void selectIssue27(ValueChangeEvent event);

  public Map<String, String> getIssue27List();

  public void initIssue27List();

  public void setNewPerson177(String p);

  public String getNewPerson177();

  public void selectPerson177(ValueChangeEvent event);

  public Map<String, String> getPerson177List();

  public void initPerson177List();

  public List<Person> getPerson101List();

  public void initPerson101List();

  public List<ResearchProject> getProject101List();

  public void initProject101List();

  public Bug getBug();

  public void setBug(Bug bug);
}