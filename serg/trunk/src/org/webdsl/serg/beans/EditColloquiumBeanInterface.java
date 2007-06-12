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

  public void setPerson9(Person person130);

  public void setResearchGroup0(ResearchGroup researchGroup5);

  public void removeResearchProject1(ResearchProject researchProject7);

  public void addResearchProject1(ResearchProject researchProject7);

  public void removePresentation0(Presentation presentation6);

  public void addPresentation0(Presentation presentation6);

  public String cancel();

  public String save();

  public void setNewPerson129(String p);

  public String getNewPerson129();

  public void selectPerson129(ValueChangeEvent event);

  public Map<String, String> getPerson129List();

  public void initPerson129List();

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

  public List<Person> getPerson49List();

  public void initPerson49List();

  public List<ResearchProject> getProject44List();

  public void initProject44List();
}