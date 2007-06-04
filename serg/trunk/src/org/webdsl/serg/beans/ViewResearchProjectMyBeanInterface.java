package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchProjectMyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProject(ResearchProject project);

  public ResearchProject getProject();

  public List<Person> getPerson10List();

  public void initPerson10List();
}