package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person182);

  public void removeResearchProject3(ResearchProject researchProject22);

  public void addResearchProject3(ResearchProject researchProject22);

  public String cancel();

  public String save();

  public void setNewPerson181(String p);

  public String getNewPerson181();

  public void selectPerson181(ValueChangeEvent event);

  public Map<String, String> getPerson181List();

  public void initPerson181List();

  public void setNewResearchProject23(String p);

  public String getNewResearchProject23();

  public void selectResearchProject23(ValueChangeEvent event);

  public Map<String, String> getResearchProject23List();

  public void initResearchProject23List();

  public List<Person> getPerson63List();

  public void initPerson63List();

  public List<ResearchProject> getProject50List();

  public void initProject50List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}