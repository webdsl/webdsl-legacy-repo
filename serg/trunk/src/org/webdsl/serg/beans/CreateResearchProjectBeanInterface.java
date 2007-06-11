package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson6(Person person56);

  public void addPerson6(Person person56);

  public void setPublication4(Publication publication18);

  public void removePublication1(Publication publication19);

  public void addPublication1(Publication publication19);

  public String cancel();

  public String save();

  public void setNewPerson57(String p);

  public String getNewPerson57();

  public void selectPerson57(ValueChangeEvent event);

  public Map<String, String> getPerson57List();

  public void initPerson57List();

  public void setNewPublication17(String p);

  public String getNewPublication17();

  public void selectPublication17(ValueChangeEvent event);

  public Map<String, String> getPublication17List();

  public void initPublication17List();

  public void setNewPublication20(String p);

  public String getNewPublication20();

  public void selectPublication20(ValueChangeEvent event);

  public Map<String, String> getPublication20List();

  public void initPublication20List();

  public List<Person> getPerson1041List();

  public void initPerson1041List();

  public List<ResearchProject> getProject1141List();

  public void initProject1141List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}