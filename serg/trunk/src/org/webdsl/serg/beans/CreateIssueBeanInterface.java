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

  public void removePerson15(Person person166);

  public void addPerson15(Person person166);

  public String cancel();

  public String save();

  public void setNewIssue17(String p);

  public String getNewIssue17();

  public void selectIssue17(ValueChangeEvent event);

  public Map<String, String> getIssue17List();

  public void initIssue17List();

  public void setNewPerson167(String p);

  public String getNewPerson167();

  public void selectPerson167(ValueChangeEvent event);

  public Map<String, String> getPerson167List();

  public void initPerson167List();

  public List<Person> getPerson93List();

  public void initPerson93List();

  public List<ResearchProject> getProject88List();

  public void initProject88List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}