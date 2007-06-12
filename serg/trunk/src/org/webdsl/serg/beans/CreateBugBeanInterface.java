package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue6(Issue issue29);

  public void addIssue6(Issue issue29);

  public void removePerson24(Person person200);

  public void addPerson24(Person person200);

  public String cancel();

  public String save();

  public void setNewIssue30(String p);

  public String getNewIssue30();

  public void selectIssue30(ValueChangeEvent event);

  public Map<String, String> getIssue30List();

  public void initIssue30List();

  public void setNewPerson201(String p);

  public String getNewPerson201();

  public void selectPerson201(ValueChangeEvent event);

  public Map<String, String> getPerson201List();

  public void initPerson201List();

  public List<Person> getPerson100List();

  public void initPerson100List();

  public List<ResearchProject> getProject100List();

  public void initProject100List();

  public Bug getBug();

  public void setBug(Bug bug);
}