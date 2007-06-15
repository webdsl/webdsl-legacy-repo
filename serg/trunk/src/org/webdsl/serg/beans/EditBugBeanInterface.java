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

  public void removeIssue4(Issue issue28);

  public void addIssue4(Issue issue28);

  public void removePerson18(Person person196);

  public void addPerson18(Person person196);

  public String cancel();

  public String save();

  public void setNewIssue29(String p);

  public String getNewIssue29();

  public void selectIssue29(ValueChangeEvent event);

  public Map<String, String> getIssue29List();

  public void initIssue29List();

  public void setNewPerson197(String p);

  public String getNewPerson197();

  public void selectPerson197(ValueChangeEvent event);

  public Map<String, String> getPerson197List();

  public void initPerson197List();

  public List<Person> getPerson108List();

  public void initPerson108List();

  public List<ResearchProject> getProject100List();

  public void initProject100List();
}