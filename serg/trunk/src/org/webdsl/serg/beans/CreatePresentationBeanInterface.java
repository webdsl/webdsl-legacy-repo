package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson13(Person person138);

  public void removeResearchProject5(ResearchProject researchProject16);

  public void addResearchProject5(ResearchProject researchProject16);

  public String cancel();

  public String save();

  public void setNewPerson137(String p);

  public String getNewPerson137();

  public void selectPerson137(ValueChangeEvent event);

  public Map<String, String> getPerson137List();

  public void initPerson137List();

  public void setNewResearchProject17(String p);

  public String getNewResearchProject17();

  public void selectResearchProject17(ValueChangeEvent event);

  public Map<String, String> getResearchProject17List();

  public void initResearchProject17List();

  public List<Person> getPerson53List();

  public void initPerson53List();

  public List<ResearchProject> getProject48List();

  public void initProject48List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}