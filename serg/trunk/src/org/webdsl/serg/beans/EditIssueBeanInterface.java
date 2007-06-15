package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setIssue(Issue issue);

  public Issue getIssue();

  public void removeIssue0(Issue issue18);

  public void addIssue0(Issue issue18);

  public void removePerson14(Person person186);

  public void addPerson14(Person person186);

  public String cancel();

  public String save();

  public void setNewIssue19(String p);

  public String getNewIssue19();

  public void selectIssue19(ValueChangeEvent event);

  public Map<String, String> getIssue19List();

  public void initIssue19List();

  public void setNewPerson187(String p);

  public String getNewPerson187();

  public void selectPerson187(ValueChangeEvent event);

  public Map<String, String> getPerson187List();

  public void initPerson187List();

  public List<Person> getPerson98List();

  public void initPerson98List();

  public List<ResearchProject> getProject87List();

  public void initProject87List();
}