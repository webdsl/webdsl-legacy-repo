package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson10(Person person162);

  public void removeResearchProject3(ResearchProject researchProject21);

  public void addResearchProject3(ResearchProject researchProject21);

  public String cancel();

  public String save();

  public void setNewPerson161(String p);

  public String getNewPerson161();

  public void selectPerson161(ValueChangeEvent event);

  public Map<String, String> getPerson161List();

  public void initPerson161List();

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