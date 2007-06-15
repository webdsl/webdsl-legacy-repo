package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person147);

  public void removeResearchProject3(ResearchProject researchProject11);

  public void addResearchProject3(ResearchProject researchProject11);

  public String cancel();

  public String save();

  public void setNewPerson146(String p);

  public String getNewPerson146();

  public void selectPerson146(ValueChangeEvent event);

  public Map<String, String> getPerson146List();

  public void initPerson146List();

  public void setNewResearchProject12(String p);

  public String getNewResearchProject12();

  public void selectResearchProject12(ValueChangeEvent event);

  public Map<String, String> getResearchProject12List();

  public void initResearchProject12List();

  public List<Person> getPerson55List();

  public void initPerson55List();

  public List<ResearchProject> getProject50List();

  public void initProject50List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}