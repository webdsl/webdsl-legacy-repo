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

  public void removeIssue3(Issue issue22);

  public void addIssue3(Issue issue22);

  public void removePerson21(Person person193);

  public void addPerson21(Person person193);

  public String cancel();

  public String save();

  public void setNewIssue23(String p);

  public String getNewIssue23();

  public void selectIssue23(ValueChangeEvent event);

  public Map<String, String> getIssue23List();

  public void initIssue23List();

  public void setNewPerson194(String p);

  public String getNewPerson194();

  public void selectPerson194(ValueChangeEvent event);

  public Map<String, String> getPerson194List();

  public void initPerson194List();

  public List<Person> getPerson95List();

  public void initPerson95List();

  public List<ResearchProject> getProject90List();

  public void initProject90List();
}