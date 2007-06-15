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

  public void removePerson15(Person person184);

  public void addPerson15(Person person184);

  public String cancel();

  public String save();

  public void setNewIssue17(String p);

  public String getNewIssue17();

  public void selectIssue17(ValueChangeEvent event);

  public Map<String, String> getIssue17List();

  public void initIssue17List();

  public void setNewPerson185(String p);

  public String getNewPerson185();

  public void selectPerson185(ValueChangeEvent event);

  public Map<String, String> getPerson185List();

  public void initPerson185List();

  public List<Person> getPerson94List();

  public void initPerson94List();

  public List<ResearchProject> getProject89List();

  public void initProject89List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}