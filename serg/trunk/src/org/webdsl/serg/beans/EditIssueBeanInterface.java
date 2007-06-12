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

  public void removeIssue0(Issue issue14);

  public void addIssue0(Issue issue14);

  public void removePerson18(Person person185);

  public void addPerson18(Person person185);

  public String cancel();

  public String save();

  public void setNewIssue15(String p);

  public String getNewIssue15();

  public void selectIssue15(ValueChangeEvent event);

  public Map<String, String> getIssue15List();

  public void initIssue15List();

  public void setNewPerson186(String p);

  public String getNewPerson186();

  public void selectPerson186(ValueChangeEvent event);

  public Map<String, String> getPerson186List();

  public void initPerson186List();

  public List<Person> getPerson91List();

  public void initPerson91List();

  public List<ResearchProject> getProject86List();

  public void initProject86List();
}