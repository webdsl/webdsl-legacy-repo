package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson8(Person person158);

  public void setResearchGroup1(ResearchGroup researchGroup11);

  public void removeResearchProject1(ResearchProject researchProject16);

  public void addResearchProject1(ResearchProject researchProject16);

  public void removePresentation1(Presentation presentation9);

  public void addPresentation1(Presentation presentation9);

  public String cancel();

  public String save();

  public void setNewPerson157(String p);

  public String getNewPerson157();

  public void selectPerson157(ValueChangeEvent event);

  public Map<String, String> getPerson157List();

  public void initPerson157List();

  public void setNewResearchGroup10(String p);

  public String getNewResearchGroup10();

  public void selectResearchGroup10(ValueChangeEvent event);

  public Map<String, String> getResearchGroup10List();

  public void initResearchGroup10List();

  public void setNewResearchProject17(String p);

  public String getNewResearchProject17();

  public void selectResearchProject17(ValueChangeEvent event);

  public Map<String, String> getResearchProject17List();

  public void initResearchProject17List();

  public void setNewPresentation10(String p);

  public String getNewPresentation10();

  public void selectPresentation10(ValueChangeEvent event);

  public Map<String, String> getPresentation10List();

  public void initPresentation10List();

  public List<Person> getPerson57List();

  public void initPerson57List();

  public List<ResearchProject> getProject47List();

  public void initProject47List();

  public Colloquium getColloquium();

  public void setColloquium(Colloquium colloquium);
}