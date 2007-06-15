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

  public void removePerson10(Person person178);

  public void addPerson10(Person person178);

  public void setPublication2(Publication publication27);

  public void removePublication0(Publication publication28);

  public void addPublication0(Publication publication28);

  public String cancel();

  public String save();

  public void setNewPerson179(String p);

  public String getNewPerson179();

  public void selectPerson179(ValueChangeEvent event);

  public Map<String, String> getPerson179List();

  public void initPerson179List();

  public void setNewPublication26(String p);

  public String getNewPublication26();

  public void selectPublication26(ValueChangeEvent event);

  public Map<String, String> getPublication26List();

  public void initPublication26List();

  public void setNewPublication29(String p);

  public String getNewPublication29();

  public void selectPublication29(ValueChangeEvent event);

  public Map<String, String> getPublication29List();

  public void initPublication29List();

  public List<Person> getPerson87List();

  public void initPerson87List();

  public List<ResearchProject> getProject77List();

  public void initProject77List();
}