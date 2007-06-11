package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue1(Issue issue14);

  public void addIssue1(Issue issue14);

  public void removePerson12(Person person71);

  public void addPerson12(Person person71);

  public String cancel();

  public String save();

  public void setNewIssue15(String p);

  public String getNewIssue15();

  public void selectIssue15(ValueChangeEvent event);

  public Map<String, String> getIssue15List();

  public void initIssue15List();

  public void setNewPerson72(String p);

  public String getNewPerson72();

  public void selectPerson72(ValueChangeEvent event);

  public Map<String, String> getPerson72List();

  public void initPerson72List();

  public List<Person> getPerson1049List();

  public void initPerson1049List();

  public List<ResearchProject> getProject1149List();

  public void initProject1149List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}