package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue1(Issue issue26);

  public void addIssue1(Issue issue26);

  public void removePerson15(Person person200);

  public void addPerson15(Person person200);

  public String cancel();

  public String save();

  public void setNewIssue27(String p);

  public String getNewIssue27();

  public void selectIssue27(ValueChangeEvent event);

  public Map<String, String> getIssue27List();

  public void initIssue27List();

  public void setNewPerson201(String p);

  public String getNewPerson201();

  public void selectPerson201(ValueChangeEvent event);

  public Map<String, String> getPerson201List();

  public void initPerson201List();

  public List<Person> getPerson99List();

  public void initPerson99List();

  public List<ResearchProject> getProject89List();

  public void initProject89List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}