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

  public void removePerson18(Person person174);

  public void addPerson18(Person person174);

  public String cancel();

  public String save();

  public void setNewIssue25(String p);

  public String getNewIssue25();

  public void selectIssue25(ValueChangeEvent event);

  public Map<String, String> getIssue25List();

  public void initIssue25List();

  public void setNewPerson175(String p);

  public String getNewPerson175();

  public void selectPerson175(ValueChangeEvent event);

  public Map<String, String> getPerson175List();

  public void initPerson175List();

  public List<Person> getPerson100List();

  public void initPerson100List();

  public List<ResearchProject> getProject100List();

  public void initProject100List();
}