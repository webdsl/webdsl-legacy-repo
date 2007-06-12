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

  public void setPerson12(Person person136);

  public void removeResearchProject4(ResearchProject researchProject14);

  public void addResearchProject4(ResearchProject researchProject14);

  public String cancel();

  public String save();

  public void setNewPerson135(String p);

  public String getNewPerson135();

  public void selectPerson135(ValueChangeEvent event);

  public Map<String, String> getPerson135List();

  public void initPerson135List();

  public void setNewResearchProject15(String p);

  public String getNewResearchProject15();

  public void selectResearchProject15(ValueChangeEvent event);

  public Map<String, String> getResearchProject15List();

  public void initResearchProject15List();

  public List<Person> getPerson52List();

  public void initPerson52List();

  public List<ResearchProject> getProject47List();

  public void initProject47List();
}