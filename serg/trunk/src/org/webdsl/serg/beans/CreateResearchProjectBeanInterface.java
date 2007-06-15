package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson11(Person person175);

  public void addPerson11(Person person175);

  public void setPublication3(Publication publication24);

  public void removePublication1(Publication publication25);

  public void addPublication1(Publication publication25);

  public String cancel();

  public String save();

  public void setNewPerson176(String p);

  public String getNewPerson176();

  public void selectPerson176(ValueChangeEvent event);

  public Map<String, String> getPerson176List();

  public void initPerson176List();

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

  public List<Person> getPerson83List();

  public void initPerson83List();

  public List<ResearchProject> getProject78List();

  public void initProject78List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}