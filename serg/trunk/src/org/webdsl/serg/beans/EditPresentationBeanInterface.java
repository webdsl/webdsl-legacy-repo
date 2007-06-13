package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPresentation(Presentation presentation);

  public Presentation getPresentation();

  public void setPerson8(Person person127);

  public void removeResearchProject2(ResearchProject researchProject9);

  public void addResearchProject2(ResearchProject researchProject9);

  public String cancel();

  public String save();

  public void setNewPerson126(String p);

  public String getNewPerson126();

  public void selectPerson126(ValueChangeEvent event);

  public Map<String, String> getPerson126List();

  public void initPerson126List();

  public void setNewResearchProject10(String p);

  public String getNewResearchProject10();

  public void selectResearchProject10(ValueChangeEvent event);

  public Map<String, String> getResearchProject10List();

  public void initResearchProject10List();

  public List<Person> getPerson53List();

  public void initPerson53List();

  public List<ResearchProject> getProject48List();

  public void initProject48List();
}