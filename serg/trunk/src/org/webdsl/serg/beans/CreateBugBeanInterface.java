package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBugBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue6(Issue issue27);

  public void addIssue6(Issue issue27);

  public void removePerson17(Person person84);

  public void addPerson17(Person person84);

  public String cancel();

  public String save();

  public void setNewIssue28(String p);

  public String getNewIssue28();

  public void selectIssue28(ValueChangeEvent event);

  public Map<String, String> getIssue28List();

  public void initIssue28List();

  public void setNewPerson85(String p);

  public String getNewPerson85();

  public void selectPerson85(ValueChangeEvent event);

  public Map<String, String> getPerson85List();

  public void initPerson85List();

  public List<Person> getPerson1055List();

  public void initPerson1055List();

  public List<ResearchProject> getProject1155List();

  public void initProject1155List();

  public Bug getBug();

  public void setBug(Bug bug);
}