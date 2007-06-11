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

  public void removePerson5(Person person54);

  public void addPerson5(Person person54);

  public void setPublication3(Publication publication14);

  public void removePublication0(Publication publication15);

  public void addPublication0(Publication publication15);

  public String cancel();

  public String save();

  public void setNewPerson55(String p);

  public String getNewPerson55();

  public void selectPerson55(ValueChangeEvent event);

  public Map<String, String> getPerson55List();

  public void initPerson55List();

  public void setNewPublication13(String p);

  public String getNewPublication13();

  public void selectPublication13(ValueChangeEvent event);

  public Map<String, String> getPublication13List();

  public void initPublication13List();

  public void setNewPublication16(String p);

  public String getNewPublication16();

  public void selectPublication16(ValueChangeEvent event);

  public Map<String, String> getPublication16List();

  public void initPublication16List();

  public List<Person> getPerson1040List();

  public void initPerson1040List();

  public List<ResearchProject> getProject1140List();

  public void initProject1140List();
}