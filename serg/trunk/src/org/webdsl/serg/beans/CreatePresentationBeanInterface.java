package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson13(Person person40);

  public void removeResearchProject5(ResearchProject researchProject14);

  public void addResearchProject5(ResearchProject researchProject14);

  public String cancel();

  public String save();

  public void setNewPerson39(String p);

  public String getNewPerson39();

  public void selectPerson39(ValueChangeEvent event);

  public Map<String, String> getPerson39List();

  public void initPerson39List();

  public void setNewResearchProject15(String p);

  public String getNewResearchProject15();

  public void selectResearchProject15(ValueChangeEvent event);

  public Map<String, String> getResearchProject15List();

  public void initResearchProject15List();

  public List<Person> getPerson1032List();

  public void initPerson1032List();

  public List<ResearchProject> getProject1132List();

  public void initProject1132List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}