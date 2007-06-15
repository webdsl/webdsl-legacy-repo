package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateIssueBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue1(Issue issue20);

  public void addIssue1(Issue issue20);

  public void removePerson15(Person person188);

  public void addPerson15(Person person188);

  public String cancel();

  public String save();

  public void setNewIssue21(String p);

  public String getNewIssue21();

  public void selectIssue21(ValueChangeEvent event);

  public Map<String, String> getIssue21List();

  public void initIssue21List();

  public void setNewPerson189(String p);

  public String getNewPerson189();

  public void selectPerson189(ValueChangeEvent event);

  public Map<String, String> getPerson189List();

  public void initPerson189List();

  public List<Person> getPerson99List();

  public void initPerson99List();

  public List<ResearchProject> getProject88List();

  public void initProject88List();

  public Issue getIssue();

  public void setIssue(Issue issue);
}