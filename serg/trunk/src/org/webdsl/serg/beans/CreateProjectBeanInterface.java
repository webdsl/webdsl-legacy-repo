package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue3(Issue issue31);

  public void addIssue3(Issue issue31);

  public void removePerson17(Person person205);

  public void addPerson17(Person person205);

  public String cancel();

  public String save();

  public void setNewIssue32(String p);

  public String getNewIssue32();

  public void selectIssue32(ValueChangeEvent event);

  public Map<String, String> getIssue32List();

  public void initIssue32List();

  public void setNewPerson206(String p);

  public String getNewPerson206();

  public void selectPerson206(ValueChangeEvent event);

  public Map<String, String> getPerson206List();

  public void initPerson206List();

  public List<Person> getPerson103List();

  public void initPerson103List();

  public List<ResearchProject> getProject94List();

  public void initProject94List();

  public Project getProject();

  public void setProject(Project project);
}