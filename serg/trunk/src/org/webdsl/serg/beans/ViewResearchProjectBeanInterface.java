package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchProjectBeanInterface 
{ 
  public void setPr(ResearchProject pr);

  public ResearchProject getPr();

  public void initialize();

  public void destroy();
}