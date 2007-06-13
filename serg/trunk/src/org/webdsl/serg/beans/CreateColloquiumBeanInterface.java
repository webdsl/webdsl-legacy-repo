package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson7(Person person125);

  public void setResearchGroup1(ResearchGroup researchGroup7);

  public void removeResearchProject1(ResearchProject researchProject7);

  public void addResearchProject1(ResearchProject researchProject7);

  public void removePresentation1(Presentation presentation8);

  public void addPresentation1(Presentation presentation8);

  public String cancel();

  public String save();

  public void setNewPerson124(String p);

  public String getNewPerson124();

  public void selectPerson124(ValueChangeEvent event);

  public Map<String, String> getPerson124List();

  public void initPerson124List();

  public void setNewResearchGroup6(String p);

  public String getNewResearchGroup6();

  public void selectResearchGroup6(ValueChangeEvent event);

  public Map<String, String> getResearchGroup6List();

  public void initResearchGroup6List();

  public void setNewResearchProject8(String p);

  public String getNewResearchProject8();

  public void selectResearchProject8(ValueChangeEvent event);

  public Map<String, String> getResearchProject8List();

  public void initResearchProject8List();

  public void setNewPresentation9(String p);

  public String getNewPresentation9();

  public void selectPresentation9(ValueChangeEvent event);

  public Map<String, String> getPresentation9List();

  public void initPresentation9List();

  public List<Person> getPerson51List();

  public void initPerson51List();

  public List<ResearchProject> getProject46List();

  public void initProject46List();

  public Colloquium getColloquium();

  public void setColloquium(Colloquium colloquium);
}