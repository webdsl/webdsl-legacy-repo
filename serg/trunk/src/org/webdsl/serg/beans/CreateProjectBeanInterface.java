package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeIssue4(Issue issue22);

  public void addIssue4(Issue issue22);

  public void removePerson15(Person person79);

  public void addPerson15(Person person79);

  public String cancel();

  public String save();

  public void setNewIssue23(String p);

  public String getNewIssue23();

  public void selectIssue23(ValueChangeEvent event);

  public Map<String, String> getIssue23List();

  public void initIssue23List();

  public void setNewPerson80(String p);

  public String getNewPerson80();

  public void selectPerson80(ValueChangeEvent event);

  public Map<String, String> getPerson80List();

  public void initPerson80List();

  public List<Person> getPerson1052List();

  public void initPerson1052List();

  public List<ResearchProject> getProject1152List();

  public void initProject1152List();

  public Project getProject();

  public void setProject(Project project);
}