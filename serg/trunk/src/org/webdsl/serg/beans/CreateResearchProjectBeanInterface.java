package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson13(Person person201);

  public void addPerson13(Person person201);

  public void setPublication3(Publication publication31);

  public void removePublication1(Publication publication32);

  public void addPublication1(Publication publication32);

  public String cancel();

  public String save();

  public void setNewPerson202(String p);

  public String getNewPerson202();

  public void selectPerson202(ValueChangeEvent event);

  public Map<String, String> getPerson202List();

  public void initPerson202List();

  public void setNewPublication30(String p);

  public String getNewPublication30();

  public void selectPublication30(ValueChangeEvent event);

  public Map<String, String> getPublication30List();

  public void initPublication30List();

  public void setNewPublication33(String p);

  public String getNewPublication33();

  public void selectPublication33(ValueChangeEvent event);

  public Map<String, String> getPublication33List();

  public void initPublication33List();

  public List<Person> getPerson93List();

  public void initPerson93List();

  public List<ResearchProject> getProject82List();

  public void initProject82List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}