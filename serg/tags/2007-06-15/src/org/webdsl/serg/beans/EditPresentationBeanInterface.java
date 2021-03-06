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

  public void setPerson9(Person person150);

  public void removeResearchProject2(ResearchProject researchProject18);

  public void addResearchProject2(ResearchProject researchProject18);

  public String cancel();

  public String save();

  public void setNewPerson149(String p);

  public String getNewPerson149();

  public void selectPerson149(ValueChangeEvent event);

  public Map<String, String> getPerson149List();

  public void initPerson149List();

  public void setNewResearchProject19(String p);

  public String getNewResearchProject19();

  public void selectResearchProject19(ValueChangeEvent event);

  public Map<String, String> getResearchProject19List();

  public void initResearchProject19List();

  public List<Person> getPerson59List();

  public void initPerson59List();

  public List<ResearchProject> getProject49List();

  public void initProject49List();
}