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

  public void setPerson9(Person person180);

  public void removeResearchProject2(ResearchProject researchProject19);

  public void addResearchProject2(ResearchProject researchProject19);

  public String cancel();

  public String save();

  public void setNewPerson179(String p);

  public String getNewPerson179();

  public void selectPerson179(ValueChangeEvent event);

  public Map<String, String> getPerson179List();

  public void initPerson179List();

  public void setNewResearchProject21(String p);

  public String getNewResearchProject21();

  public void selectResearchProject21(ValueChangeEvent event);

  public Map<String, String> getResearchProject21List();

  public void initResearchProject21List();

  public List<Person> getPerson62List();

  public void initPerson62List();

  public List<ResearchProject> getProject49List();

  public void initProject49List();
}