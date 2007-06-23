package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person168);

  public void removeResearchProject3(ResearchProject researchProject22);

  public void addResearchProject3(ResearchProject researchProject22);

  public String cancel();

  public String save();

  public void setNewPerson167(String p);

  public String getNewPerson167();

  public void selectPerson167(ValueChangeEvent event);

  public Map<String, String> getPerson167List();

  public void initPerson167List();

  public void setNewResearchProject23(String p);

  public String getNewResearchProject23();

  public void selectResearchProject23(ValueChangeEvent event);

  public Map<String, String> getResearchProject23List();

  public void initResearchProject23List();

  public List<Person> getPerson61List();

  public void initPerson61List();

  public List<ResearchProject> getProject50List();

  public void initProject50List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}