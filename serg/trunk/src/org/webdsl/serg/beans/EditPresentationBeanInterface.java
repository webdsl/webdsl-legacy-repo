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

  public void setPerson9(Person person166);

  public void removeResearchProject2(ResearchProject researchProject19);

  public void addResearchProject2(ResearchProject researchProject19);

  public String cancel();

  public String save();

  public void setNewPerson165(String p);

  public String getNewPerson165();

  public void selectPerson165(ValueChangeEvent event);

  public Map<String, String> getPerson165List();

  public void initPerson165List();

  public void setNewResearchProject21(String p);

  public String getNewResearchProject21();

  public void selectResearchProject21(ValueChangeEvent event);

  public Map<String, String> getResearchProject21List();

  public void initResearchProject21List();

  public List<Person> getPerson60List();

  public void initPerson60List();

  public List<ResearchProject> getProject49List();

  public void initProject49List();
}