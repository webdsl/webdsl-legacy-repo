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

  public void removeIssue0(Issue issue12);

  public void addIssue0(Issue issue12);

  public void removePerson11(Person person69);

  public void addPerson11(Person person69);

  public String cancel();

  public String save();

  public void setNewIssue13(String p);

  public String getNewIssue13();

  public void selectIssue13(ValueChangeEvent event);

  public Map<String, String> getIssue13List();

  public void initIssue13List();

  public void setNewPerson70(String p);

  public String getNewPerson70();

  public void selectPerson70(ValueChangeEvent event);

  public Map<String, String> getPerson70List();

  public void initPerson70List();

  public List<Person> getPerson1048List();

  public void initPerson1048List();

  public List<ResearchProject> getProject1148List();

  public void initProject1148List();
}