package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson8(Person person143);

  public void setResearchGroup1(ResearchGroup researchGroup7);

  public void removeResearchProject1(ResearchProject researchProject7);

  public void addResearchProject1(ResearchProject researchProject7);

  public void removePresentation1(Presentation presentation9);

  public void addPresentation1(Presentation presentation9);

  public String cancel();

  public String save();

  public void setNewPerson142(String p);

  public String getNewPerson142();

  public void selectPerson142(ValueChangeEvent event);

  public Map<String, String> getPerson142List();

  public void initPerson142List();

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

  public void setNewPresentation10(String p);

  public String getNewPresentation10();

  public void selectPresentation10(ValueChangeEvent event);

  public Map<String, String> getPresentation10List();

  public void initPresentation10List();

  public List<Person> getPerson52List();

  public void initPerson52List();

  public List<ResearchProject> getProject47List();

  public void initProject47List();

  public Colloquium getColloquium();

  public void setColloquium(Colloquium colloquium);
}