package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person132);

  public void setResearchGroup1(ResearchGroup researchGroup7);

  public void removeResearchProject2(ResearchProject researchProject9);

  public void addResearchProject2(ResearchProject researchProject9);

  public void removePresentation1(Presentation presentation8);

  public void addPresentation1(Presentation presentation8);

  public String cancel();

  public String save();

  public void setNewPerson131(String p);

  public String getNewPerson131();

  public void selectPerson131(ValueChangeEvent event);

  public Map<String, String> getPerson131List();

  public void initPerson131List();

  public void setNewResearchGroup6(String p);

  public String getNewResearchGroup6();

  public void selectResearchGroup6(ValueChangeEvent event);

  public Map<String, String> getResearchGroup6List();

  public void initResearchGroup6List();

  public void setNewResearchProject10(String p);

  public String getNewResearchProject10();

  public void selectResearchProject10(ValueChangeEvent event);

  public Map<String, String> getResearchProject10List();

  public void initResearchProject10List();

  public void setNewPresentation9(String p);

  public String getNewPresentation9();

  public void selectPresentation9(ValueChangeEvent event);

  public Map<String, String> getPresentation9List();

  public void initPresentation9List();

  public List<Person> getPerson50List();

  public void initPerson50List();

  public List<ResearchProject> getProject45List();

  public void initProject45List();

  public Colloquium getColloquium();

  public void setColloquium(Colloquium colloquium);
}