package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson9(Person person151);

  public void removeResearchProject3(ResearchProject researchProject16);

  public void addResearchProject3(ResearchProject researchProject16);

  public String cancel();

  public String save();

  public void setNewPerson150(String p);

  public String getNewPerson150();

  public void selectPerson150(ValueChangeEvent event);

  public Map<String, String> getPerson150List();

  public void initPerson150List();

  public void setNewResearchProject17(String p);

  public String getNewResearchProject17();

  public void selectResearchProject17(ValueChangeEvent event);

  public Map<String, String> getResearchProject17List();

  public void initResearchProject17List();

  public List<Person> getPerson54List();

  public void initPerson54List();

  public List<ResearchProject> getProject49List();

  public void initProject49List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}