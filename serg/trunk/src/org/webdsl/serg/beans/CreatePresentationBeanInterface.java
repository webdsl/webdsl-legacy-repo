package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person163);

  public void removeResearchProject3(ResearchProject researchProject21);

  public void addResearchProject3(ResearchProject researchProject21);

  public String cancel();

  public String save();

  public void setNewPerson162(String p);

  public String getNewPerson162();

  public void selectPerson162(ValueChangeEvent event);

  public Map<String, String> getPerson162List();

  public void initPerson162List();

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