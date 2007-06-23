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

  public String createNewPerson(ResearchProject researchProject30, java.util.Set<Person> members0);

  public String createNewPublication(ResearchProject researchProject40, java.util.Set<Publication> publications3);

  public List<Person> getPerson94List();

  public void initPerson94List();

  public List<ResearchProject> getProject83List();

  public void initProject83List();
}