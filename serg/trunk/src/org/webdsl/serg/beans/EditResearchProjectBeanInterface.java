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

  public void removePerson12(Person person170);

  public void addPerson12(Person person170);

  public void setPublication3(Publication publication22);

  public void removePublication0(Publication publication23);

  public void addPublication0(Publication publication23);

  public String cancel();

  public String save();

  public void setNewPerson171(String p);

  public String getNewPerson171();

  public void selectPerson171(ValueChangeEvent event);

  public Map<String, String> getPerson171List();

  public void initPerson171List();

  public void setNewPublication21(String p);

  public String getNewPublication21();

  public void selectPublication21(ValueChangeEvent event);

  public Map<String, String> getPublication21List();

  public void initPublication21List();

  public void setNewPublication24(String p);

  public String getNewPublication24();

  public void selectPublication24(ValueChangeEvent event);

  public Map<String, String> getPublication24List();

  public void initPublication24List();

  public List<Person> getPerson80List();

  public void initPerson80List();

  public List<ResearchProject> getProject75List();

  public void initProject75List();
}