package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeResearchProject(ResearchProject researchProject14);

  public List<Person> getPerson97List();

  public void initPerson97List();

  public List<ResearchProject> getProject84List();

  public void initProject84List();

  public List<ResearchProject> getResearchProject9List();

  public void initResearchProject9List();
}