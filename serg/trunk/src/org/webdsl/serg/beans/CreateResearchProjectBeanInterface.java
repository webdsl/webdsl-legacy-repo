package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson4(Person person44);

  public void addPerson4(Person person44);

  public void setPublication4(Publication publication15);

  public void removePublication1(Publication publication16);

  public void addPublication1(Publication publication16);

  public String cancel();

  public String save();

  public void setNewPerson45(String p);

  public String getNewPerson45();

  public void selectPerson45(ValueChangeEvent event);

  public Map<String, String> getPerson45List();

  public void initPerson45List();

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

  public List<Person> getPerson1040List();

  public void initPerson1040List();

  public List<ResearchProject> getProject1140List();

  public void initProject1140List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}