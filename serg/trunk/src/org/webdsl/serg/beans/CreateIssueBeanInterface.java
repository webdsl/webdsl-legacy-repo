package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue1(Issue issue16);

  public void addIssue1(Issue issue16);

  public void removePerson19(Person person187);

  public void addPerson19(Person person187);

  public String cancel();

  public String save();

  public void setNewIssue17(String p);

  public String getNewIssue17();

  public void selectIssue17(ValueChangeEvent event);

  public Map<String, String> getIssue17List();

  public void initIssue17List();

  public void setNewPerson188(String p);

  public String getNewPerson188();

  public void selectPerson188(ValueChangeEvent event);

  public Map<String, String> getPerson188List();

  public void initPerson188List();

  public List<Person> getPerson92List();

  public void initPerson92List();

  public List<ResearchProject> getProject87List();

  public void initProject87List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}