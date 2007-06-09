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

  public void setPerson12(Person person32);

  public void removeResearchProject4(ResearchProject researchProject12);

  public void addResearchProject4(ResearchProject researchProject12);

  public String cancel();

  public String save();

  public void setNewPerson31(String p);

  public String getNewPerson31();

  public void selectPerson31(ValueChangeEvent event);

  public Map<String, String> getPerson31List();

  public void initPerson31List();

  public void setNewResearchProject13(String p);

  public String getNewResearchProject13();

  public void selectResearchProject13(ValueChangeEvent event);

  public Map<String, String> getResearchProject13List();

  public void initResearchProject13List();

  public List<Person> getPerson1031List();

  public void initPerson1031List();

  public List<ResearchProject> getProject1131List();

  public void initProject1131List();
}