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

  public void removeIssue0(Issue issue24);

  public void addIssue0(Issue issue24);

  public void removePerson16(Person person223);

  public void addPerson16(Person person223);

  public String cancel();

  public String save();

  public void setNewIssue25(String p);

  public String getNewIssue25();

  public void selectIssue25(ValueChangeEvent event);

  public Map<String, String> getIssue25List();

  public void initIssue25List();

  public void setNewPerson224(String p);

  public String getNewPerson224();

  public void selectPerson224(ValueChangeEvent event);

  public Map<String, String> getPerson224List();

  public void initPerson224List();

  public List<Person> getPerson105List();

  public void initPerson105List();

  public List<ResearchProject> getProject92List();

  public void initProject92List();
}