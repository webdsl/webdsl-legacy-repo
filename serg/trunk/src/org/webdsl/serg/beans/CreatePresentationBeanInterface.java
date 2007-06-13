package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson9(Person person129);

  public void removeResearchProject3(ResearchProject researchProject11);

  public void addResearchProject3(ResearchProject researchProject11);

  public String cancel();

  public String save();

  public void setNewPerson128(String p);

  public String getNewPerson128();

  public void selectPerson128(ValueChangeEvent event);

  public Map<String, String> getPerson128List();

  public void initPerson128List();

  public void setNewResearchProject12(String p);

  public String getNewResearchProject12();

  public void selectResearchProject12(ValueChangeEvent event);

  public Map<String, String> getResearchProject12List();

  public void initResearchProject12List();

  public List<Person> getPerson54List();

  public void initPerson54List();

  public List<ResearchProject> getProject49List();

  public void initProject49List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}