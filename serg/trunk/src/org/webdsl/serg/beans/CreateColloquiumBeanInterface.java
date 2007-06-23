package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson8(Person person164);

  public void setResearchGroup1(ResearchGroup researchGroup11);

  public void removeResearchProject1(ResearchProject researchProject17);

  public void addResearchProject1(ResearchProject researchProject17);

  public void removePresentation1(Presentation presentation9);

  public void addPresentation1(Presentation presentation9);

  public String cancel();

  public String save();

  public void setNewPerson163(String p);

  public String getNewPerson163();

  public void selectPerson163(ValueChangeEvent event);

  public Map<String, String> getPerson163List();

  public void initPerson163List();

  public void setNewResearchGroup10(String p);

  public String getNewResearchGroup10();

  public void selectResearchGroup10(ValueChangeEvent event);

  public Map<String, String> getResearchGroup10List();

  public void initResearchGroup10List();

  public void setNewResearchProject18(String p);

  public String getNewResearchProject18();

  public void selectResearchProject18(ValueChangeEvent event);

  public Map<String, String> getResearchProject18List();

  public void initResearchProject18List();

  public void setNewPresentation10(String p);

  public String getNewPresentation10();

  public void selectPresentation10(ValueChangeEvent event);

  public Map<String, String> getPresentation10List();

  public void initPresentation10List();

  public List<Person> getPerson58List();

  public void initPerson58List();

  public List<ResearchProject> getProject47List();

  public void initProject47List();

  public Colloquium getColloquium();

  public void setColloquium(Colloquium colloquium);
}