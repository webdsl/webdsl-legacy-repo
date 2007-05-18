package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchProjectBeanInterface 
{ 
  public void setProject(ResearchProject project);

  public ResearchProject getProject();

  public void initialize();

  public void destroy();
}