package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson11(Person person157);

  public void addPerson11(Person person157);

  public void setPublication3(Publication publication24);

  public void removePublication1(Publication publication25);

  public void addPublication1(Publication publication25);

  public String cancel();

  public String save();

  public void setNewPerson158(String p);

  public String getNewPerson158();

  public void selectPerson158(ValueChangeEvent event);

  public Map<String, String> getPerson158List();

  public void initPerson158List();

  public void setNewPublication23(String p);

  public String getNewPublication23();

  public void selectPublication23(ValueChangeEvent event);

  public Map<String, String> getPublication23List();

  public void initPublication23List();

  public void setNewPublication26(String p);

  public String getNewPublication26();

  public void selectPublication26(ValueChangeEvent event);

  public Map<String, String> getPublication26List();

  public void initPublication26List();

  public List<Person> getPerson82List();

  public void initPerson82List();

  public List<ResearchProject> getProject77List();

  public void initProject77List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}