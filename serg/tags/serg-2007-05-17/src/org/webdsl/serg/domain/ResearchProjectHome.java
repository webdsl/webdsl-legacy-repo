package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("researchProjectHome") public class ResearchProjectHome extends EntityHome<ResearchProject> 
{ 
  @Factory("researchProject") public ResearchProject initResearchProject()
  { 
    return getInstance();
  }
}