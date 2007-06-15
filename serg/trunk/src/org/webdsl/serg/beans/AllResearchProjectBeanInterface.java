package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeResearchProject(ResearchProject researchProject4);

  public List<Person> getPerson85List();

  public void initPerson85List();

  public List<ResearchProject> getProject80List();

  public void initProject80List();

  public List<ResearchProject> getResearchProject3List();

  public void initResearchProject3List();
}