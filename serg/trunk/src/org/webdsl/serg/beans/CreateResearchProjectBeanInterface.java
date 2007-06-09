package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson4(Person person45);

  public void addPerson4(Person person45);

  public void setPublication4(Publication publication15);

  public void removePublication1(Publication publication16);

  public void addPublication1(Publication publication16);

  public String cancel();

  public String save();

  public void setNewPerson46(String p);

  public String getNewPerson46();

  public void selectPerson46(ValueChangeEvent event);

  public Map<String, String> getPerson46List();

  public void initPerson46List();

  public void setNewPublication14(String p);

  public String getNewPublication14();

  public void selectPublication14(ValueChangeEvent event);

  public Map<String, String> getPublication14List();

  public void initPublication14List();

  public void setNewPublication17(String p);

  public String getNewPublication17();

  public void selectPublication17(ValueChangeEvent event);

  public Map<String, String> getPublication17List();

  public void initPublication17List();

  public List<Person> getPerson1041List();

  public void initPerson1041List();

  public List<ResearchProject> getProject1141List();

  public void initProject1141List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}