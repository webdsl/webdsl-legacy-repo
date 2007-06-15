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

  public String createNewPerson();

  public String createNewPublication();

  public List<Person> getPerson88List();

  public void initPerson88List();

  public List<ResearchProject> getProject78List();

  public void initProject78List();
}