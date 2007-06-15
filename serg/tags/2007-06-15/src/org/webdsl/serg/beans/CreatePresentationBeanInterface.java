package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person152);

  public void removeResearchProject3(ResearchProject researchProject21);

  public void addResearchProject3(ResearchProject researchProject21);

  public String cancel();

  public String save();

  public void setNewPerson151(String p);

  public String getNewPerson151();

  public void selectPerson151(ValueChangeEvent event);

  public Map<String, String> getPerson151List();

  public void initPerson151List();

  public void setNewResearchProject22(String p);

  public String getNewResearchProject22();

  public void selectResearchProject22(ValueChangeEvent event);

  public Map<String, String> getResearchProject22List();

  public void initResearchProject22List();

  public List<Person> getPerson60List();

  public void initPerson60List();

  public List<ResearchProject> getProject50List();

  public void initProject50List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}