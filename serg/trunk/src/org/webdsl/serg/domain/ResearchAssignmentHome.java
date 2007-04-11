package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("researchAssignmentHome") public class ResearchAssignmentHome extends EntityHome<ResearchAssignment> 
{ 
  @Factory("researchAssignment") public ResearchAssignment initResearchAssignment()
  { 
    return getInstance();
  }
}