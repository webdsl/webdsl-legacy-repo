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

  public void removePerson14(Person person182);

  public void addPerson14(Person person182);

  public String cancel();

  public String save();

  public void setNewIssue15(String p);

  public String getNewIssue15();

  public void selectIssue15(ValueChangeEvent event);

  public Map<String, String> getIssue15List();

  public void initIssue15List();

  public void setNewPerson183(String p);

  public String getNewPerson183();

  public void selectPerson183(ValueChangeEvent event);

  public Map<String, String> getPerson183List();

  public void initPerson183List();

  public List<Person> getPerson93List();

  public void initPerson93List();

  public List<ResearchProject> getProject88List();

  public void initProject88List();
}