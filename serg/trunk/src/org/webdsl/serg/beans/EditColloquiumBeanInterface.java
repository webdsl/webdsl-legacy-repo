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

  public void setPerson7(Person person157);

  public void setResearchGroup0(ResearchGroup researchGroup9);

  public void removeResearchProject0(ResearchProject researchProject14);

  public void addResearchProject0(ResearchProject researchProject14);

  public void removePresentation0(Presentation presentation7);

  public void addPresentation0(Presentation presentation7);

  public String cancel();

  public String save();

  public void setNewPerson156(String p);

  public String getNewPerson156();

  public void selectPerson156(ValueChangeEvent event);

  public Map<String, String> getPerson156List();

  public void initPerson156List();

  public void setNewResearchGroup8(String p);

  public String getNewResearchGroup8();

  public void selectResearchGroup8(ValueChangeEvent event);

  public Map<String, String> getResearchGroup8List();

  public void initResearchGroup8List();

  public void setNewResearchProject15(String p);

  public String getNewResearchProject15();

  public void selectResearchProject15(ValueChangeEvent event);

  public Map<String, String> getResearchProject15List();

  public void initResearchProject15List();

  public void setNewPresentation8(String p);

  public String getNewPresentation8();

  public void selectPresentation8(ValueChangeEvent event);

  public Map<String, String> getPresentation8List();

  public void initPresentation8List();

  public List<Person> getPerson56List();

  public void initPerson56List();

  public List<ResearchProject> getProject46List();

  public void initProject46List();
}