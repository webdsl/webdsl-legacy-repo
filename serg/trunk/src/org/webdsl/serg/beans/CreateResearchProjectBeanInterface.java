package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson13(Person person172);

  public void addPerson13(Person person172);

  public void setPublication4(Publication publication26);

  public void removePublication1(Publication publication27);

  public void addPublication1(Publication publication27);

  public String cancel();

  public String save();

  public void setNewPerson173(String p);

  public String getNewPerson173();

  public void selectPerson173(ValueChangeEvent event);

  public Map<String, String> getPerson173List();

  public void initPerson173List();

  public void setNewPublication25(String p);

  public String getNewPublication25();

  public void selectPublication25(ValueChangeEvent event);

  public Map<String, String> getPublication25List();

  public void initPublication25List();

  public void setNewPublication28(String p);

  public String getNewPublication28();

  public void selectPublication28(ValueChangeEvent event);

  public Map<String, String> getPublication28List();

  public void initPublication28List();

  public List<Person> getPerson81List();

  public void initPerson81List();

  public List<ResearchProject> getProject76List();

  public void initProject76List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}