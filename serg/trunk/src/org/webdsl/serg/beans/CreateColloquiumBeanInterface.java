package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person28);

  public void setResearchGroup1(ResearchGroup researchGroup5);

  public void removeResearchProject2(ResearchProject researchProject7);

  public void addResearchProject2(ResearchProject researchProject7);

  public void removePresentation1(Presentation presentation6);

  public void addPresentation1(Presentation presentation6);

  public String cancel();

  public String save();

  public void setNewPerson27(String p);

  public String getNewPerson27();

  public void selectPerson27(ValueChangeEvent event);

  public Map<String, String> getPerson27List();

  public void initPerson27List();

  public void setNewResearchGroup4(String p);

  public String getNewResearchGroup4();

  public void selectResearchGroup4(ValueChangeEvent event);

  public Map<String, String> getResearchGroup4List();

  public void initResearchGroup4List();

  public void setNewResearchProject8(String p);

  public String getNewResearchProject8();

  public void selectResearchProject8(ValueChangeEvent event);

  public Map<String, String> getResearchProject8List();

  public void initResearchProject8List();

  public void setNewPresentation7(String p);

  public String getNewPresentation7();

  public void selectPresentation7(ValueChangeEvent event);

  public Map<String, String> getPresentation7List();

  public void initPresentation7List();

  public List<Person> getPerson1030List();

  public void initPerson1030List();

  public List<ResearchProject> getProject1130List();

  public void initProject1130List();

  public Colloquium getColloquium();

  public void setColloquium(Colloquium colloquium);
}