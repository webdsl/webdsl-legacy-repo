package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson13(Person person34);

  public void removeResearchProject5(ResearchProject researchProject14);

  public void addResearchProject5(ResearchProject researchProject14);

  public String cancel();

  public String save();

  public void setNewPerson33(String p);

  public String getNewPerson33();

  public void selectPerson33(ValueChangeEvent event);

  public Map<String, String> getPerson33List();

  public void initPerson33List();

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