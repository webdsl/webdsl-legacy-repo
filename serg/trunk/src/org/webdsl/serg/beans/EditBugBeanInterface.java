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

  public void removeIssue5(Issue issue25);

  public void addIssue5(Issue issue25);

  public void removePerson16(Person person82);

  public void addPerson16(Person person82);

  public String cancel();

  public String save();

  public void setNewIssue26(String p);

  public String getNewIssue26();

  public void selectIssue26(ValueChangeEvent event);

  public Map<String, String> getIssue26List();

  public void initIssue26List();

  public void setNewPerson83(String p);

  public String getNewPerson83();

  public void selectPerson83(ValueChangeEvent event);

  public Map<String, String> getPerson83List();

  public void initPerson83List();

  public List<Person> getPerson1054List();

  public void initPerson1054List();

  public List<ResearchProject> getProject1154List();

  public void initProject1154List();
}