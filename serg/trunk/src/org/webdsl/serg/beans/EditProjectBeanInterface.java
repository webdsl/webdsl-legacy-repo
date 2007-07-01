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

  public void removePerson18(Person person228);

  public void addPerson18(Person person228);

  public String cancel();

  public String save();

  public void setNewIssue30(String p);

  public String getNewIssue30();

  public void selectIssue30(ValueChangeEvent event);

  public Map<String, String> getIssue30List();

  public void initIssue30List();

  public void setNewPerson229(String p);

  public String getNewPerson229();

  public void selectPerson229(ValueChangeEvent event);

  public Map<String, String> getPerson229List();

  public void initPerson229List();

  public List<Person> getPerson109List();

  public void initPerson109List();

  public List<ResearchProject> getProject96List();

  public void initProject96List();
}