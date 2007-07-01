package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchProjectMyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProject(ResearchProject project);

  public ResearchProject getProject();

  public List<Person> getPerson22List();

  public void initPerson22List();

  public List<ResearchProject> getProject9List();

  public void initProject9List();
}