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

  public void removePerson3(Person person43);

  public void addPerson3(Person person43);

  public void setPublication3(Publication publication11);

  public void removePublication0(Publication publication12);

  public void addPublication0(Publication publication12);

  public String cancel();

  public String save();

  public void setNewPerson44(String p);

  public String getNewPerson44();

  public void selectPerson44(ValueChangeEvent event);

  public Map<String, String> getPerson44List();

  public void initPerson44List();

  public void setNewPublication10(String p);

  public String getNewPublication10();

  public void selectPublication10(ValueChangeEvent event);

  public Map<String, String> getPublication10List();

  public void initPublication10List();

  public void setNewPublication13(String p);

  public String getNewPublication13();

  public void selectPublication13(ValueChangeEvent event);

  public Map<String, String> getPublication13List();

  public void initPublication13List();

  public List<Person> getPerson1040List();

  public void initPerson1040List();

  public List<ResearchProject> getProject1140List();

  public void initProject1140List();
}