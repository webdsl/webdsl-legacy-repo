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

  public void removePerson19(Person person216);

  public void addPerson19(Person person216);

  public String cancel();

  public String save();

  public void setNewIssue32(String p);

  public String getNewIssue32();

  public void selectIssue32(ValueChangeEvent event);

  public Map<String, String> getIssue32List();

  public void initIssue32List();

  public void setNewPerson217(String p);

  public String getNewPerson217();

  public void selectPerson217(ValueChangeEvent event);

  public Map<String, String> getPerson217List();

  public void initPerson217List();

  public List<Person> getPerson108List();

  public void initPerson108List();

  public List<ResearchProject> getProject98List();

  public void initProject98List();

  public Project getProject();

  public void setProject(Project project);
}