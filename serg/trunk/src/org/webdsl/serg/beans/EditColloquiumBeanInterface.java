package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setColloquium(Colloquium colloquium);

  public Colloquium getColloquium();

  public void setPerson9(Person person26);

  public void setResearchGroup0(ResearchGroup researchGroup3);

  public void removeResearchProject1(ResearchProject researchProject5);

  public void addResearchProject1(ResearchProject researchProject5);

  public void removePresentation0(Presentation presentation4);

  public void addPresentation0(Presentation presentation4);

  public String cancel();

  public String save();

  public void setNewPerson25(String p);

  public String getNewPerson25();

  public void selectPerson25(ValueChangeEvent event);

  public Map<String, String> getPerson25List();

  public void initPerson25List();

  public void setNewResearchGroup2(String p);

  public String getNewResearchGroup2();

  public void selectResearchGroup2(ValueChangeEvent event);

  public Map<String, String> getResearchGroup2List();

  public void initResearchGroup2List();

  public void setNewResearchProject6(String p);

  public String getNewResearchProject6();

  public void selectResearchProject6(ValueChangeEvent event);

  public Map<String, String> getResearchProject6List();

  public void initResearchProject6List();

  public void setNewPresentation5(String p);

  public String getNewPresentation5();

  public void selectPresentation5(ValueChangeEvent event);

  public Map<String, String> getPresentation5List();

  public void initPresentation5List();

  public List<Person> getPerson1029List();

  public void initPerson1029List();

  public List<ResearchProject> getProject1129List();

  public void initProject1129List();
}