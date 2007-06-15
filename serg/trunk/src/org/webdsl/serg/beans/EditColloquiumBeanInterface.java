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

  public void setPerson7(Person person141);

  public void setResearchGroup0(ResearchGroup researchGroup5);

  public void removeResearchProject0(ResearchProject researchProject5);

  public void addResearchProject0(ResearchProject researchProject5);

  public void removePresentation0(Presentation presentation7);

  public void addPresentation0(Presentation presentation7);

  public String cancel();

  public String save();

  public void setNewPerson140(String p);

  public String getNewPerson140();

  public void selectPerson140(ValueChangeEvent event);

  public Map<String, String> getPerson140List();

  public void initPerson140List();

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

  public void setNewPresentation8(String p);

  public String getNewPresentation8();

  public void selectPresentation8(ValueChangeEvent event);

  public Map<String, String> getPresentation8List();

  public void initPresentation8List();

  public List<Person> getPerson51List();

  public void initPerson51List();

  public List<ResearchProject> getProject46List();

  public void initProject46List();
}