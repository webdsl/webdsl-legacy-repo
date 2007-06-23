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

  public void removePerson20(Person person219);

  public void addPerson20(Person person219);

  public String cancel();

  public String save();

  public void setNewIssue35(String p);

  public String getNewIssue35();

  public void selectIssue35(ValueChangeEvent event);

  public Map<String, String> getIssue35List();

  public void initIssue35List();

  public void setNewPerson220(String p);

  public String getNewPerson220();

  public void selectPerson220(ValueChangeEvent event);

  public Map<String, String> getPerson220List();

  public void initPerson220List();

  public List<Person> getPerson112List();

  public void initPerson112List();

  public List<ResearchProject> getProject105List();

  public void initProject105List();
}