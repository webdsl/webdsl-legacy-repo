package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setResearchProject(ResearchProject researchProject);

  public ResearchProject getResearchProject();

  public String createNewPerson(ResearchProject researchProject20, java.util.Set<Person> members0);

  public String createNewPublication(ResearchProject researchProject30, java.util.Set<Publication> publications3);

  public List<Person> getPerson89List();

  public void initPerson89List();

  public List<ResearchProject> getProject79List();

  public void initProject79List();
}