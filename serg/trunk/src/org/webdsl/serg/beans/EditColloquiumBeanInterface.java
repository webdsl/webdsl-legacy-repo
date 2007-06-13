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

  public void setPerson6(Person person123);

  public void setResearchGroup0(ResearchGroup researchGroup5);

  public void removeResearchProject0(ResearchProject researchProject5);

  public void addResearchProject0(ResearchProject researchProject5);

  public void removePresentation0(Presentation presentation6);

  public void addPresentation0(Presentation presentation6);

  public String cancel();

  public String save();

  public void setNewPerson122(String p);

  public String getNewPerson122();

  public void selectPerson122(ValueChangeEvent event);

  public Map<String, String> getPerson122List();

  public void initPerson122List();

  public void setNewResearchGroup4(String p);

  public String getNewResearchGroup4();

  public void selectResearchGroup4(ValueChangeEvent event);

  public Map<String, String> getResearchGroup4List();

  public void initResearchGroup4List();

  public void setNewResearchProject6(String p);

  public String getNewResearchProject6();

  public void selectResearchProject6(ValueChangeEvent event);

  public Map<String, String> getResearchProject6List();

  public void initResearchProject6List();

  public void setNewPresentation7(String p);

  public String getNewPresentation7();

  public void selectPresentation7(ValueChangeEvent event);

  public Map<String, String> getPresentation7List();

  public void initPresentation7List();

  public List<Person> getPerson50List();

  public void initPerson50List();

  public List<ResearchProject> getProject45List();

  public void initProject45List();
}