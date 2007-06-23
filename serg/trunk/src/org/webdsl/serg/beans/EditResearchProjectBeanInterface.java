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

  public void removePerson12(Person person199);

  public void addPerson12(Person person199);

  public void setPublication2(Publication publication27);

  public void removePublication0(Publication publication28);

  public void addPublication0(Publication publication28);

  public String cancel();

  public String save();

  public void setNewPerson200(String p);

  public String getNewPerson200();

  public void selectPerson200(ValueChangeEvent event);

  public Map<String, String> getPerson200List();

  public void initPerson200List();

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

  public List<Person> getPerson92List();

  public void initPerson92List();

  public List<ResearchProject> getProject81List();

  public void initProject81List();
}