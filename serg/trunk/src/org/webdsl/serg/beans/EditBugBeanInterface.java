package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBug(Bug bug);

  public Bug getBug();

  public void removeIssue4(Issue issue34);

  public void addIssue4(Issue issue34);

  public void removePerson18(Person person208);

  public void addPerson18(Person person208);

  public String cancel();

  public String save();

  public void setNewIssue35(String p);

  public String getNewIssue35();

  public void selectIssue35(ValueChangeEvent event);

  public Map<String, String> getIssue35List();

  public void initIssue35List();

  public void setNewPerson209(String p);

  public String getNewPerson209();

  public void selectPerson209(ValueChangeEvent event);

  public Map<String, String> getPerson209List();

  public void initPerson209List();

  public List<Person> getPerson106List();

  public void initPerson106List();

  public List<ResearchProject> getProject101List();

  public void initProject101List();
}