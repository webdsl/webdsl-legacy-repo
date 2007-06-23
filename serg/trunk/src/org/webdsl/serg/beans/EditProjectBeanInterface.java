package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProject(Project project);

  public Project getProject();

  public void removeIssue2(Issue issue29);

  public void addIssue2(Issue issue29);

  public void removePerson18(Person person214);

  public void addPerson18(Person person214);

  public String cancel();

  public String save();

  public void setNewIssue30(String p);

  public String getNewIssue30();

  public void selectIssue30(ValueChangeEvent event);

  public Map<String, String> getIssue30List();

  public void initIssue30List();

  public void setNewPerson215(String p);

  public String getNewPerson215();

  public void selectPerson215(ValueChangeEvent event);

  public Map<String, String> getPerson215List();

  public void initPerson215List();

  public List<Person> getPerson107List();

  public void initPerson107List();

  public List<ResearchProject> getProject96List();

  public void initProject96List();
}