package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setResearchProject(ResearchProject researchProject);

  public ResearchProject getResearchProject();

  public void removePerson10(Person person177);

  public void addPerson10(Person person177);

  public void setPublication2(Publication publication21);

  public void removePublication0(Publication publication22);

  public void addPublication0(Publication publication22);

  public String cancel();

  public String save();

  public void setNewPerson178(String p);

  public String getNewPerson178();

  public void selectPerson178(ValueChangeEvent event);

  public Map<String, String> getPerson178List();

  public void initPerson178List();

  public void setNewPublication20(String p);

  public String getNewPublication20();

  public void selectPublication20(ValueChangeEvent event);

  public Map<String, String> getPublication20List();

  public void initPublication20List();

  public void setNewPublication23(String p);

  public String getNewPublication23();

  public void selectPublication23(ValueChangeEvent event);

  public Map<String, String> getPublication23List();

  public void initPublication23List();

  public List<Person> getPerson86List();

  public void initPerson86List();

  public List<ResearchProject> getProject76List();

  public void initProject76List();
}