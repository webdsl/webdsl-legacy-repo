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

  public void removeIssue3(Issue issue20);

  public void addIssue3(Issue issue20);

  public void removePerson14(Person person77);

  public void addPerson14(Person person77);

  public String cancel();

  public String save();

  public void setNewIssue21(String p);

  public String getNewIssue21();

  public void selectIssue21(ValueChangeEvent event);

  public Map<String, String> getIssue21List();

  public void initIssue21List();

  public void setNewPerson78(String p);

  public String getNewPerson78();

  public void selectPerson78(ValueChangeEvent event);

  public Map<String, String> getPerson78List();

  public void initPerson78List();

  public List<Person> getPerson1051List();

  public void initPerson1051List();

  public List<ResearchProject> getProject1151List();

  public void initProject1151List();
}