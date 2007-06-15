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

  public void removePerson14(Person person187);

  public void addPerson14(Person person187);

  public String cancel();

  public String save();

  public void setNewIssue25(String p);

  public String getNewIssue25();

  public void selectIssue25(ValueChangeEvent event);

  public Map<String, String> getIssue25List();

  public void initIssue25List();

  public void setNewPerson188(String p);

  public String getNewPerson188();

  public void selectPerson188(ValueChangeEvent event);

  public Map<String, String> getPerson188List();

  public void initPerson188List();

  public List<Person> getPerson98List();

  public void initPerson98List();

  public List<ResearchProject> getProject88List();

  public void initProject88List();
}