package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("assignmentHome") public class AssignmentHome extends EntityHome<Assignment> 
{ 
  @Factory("assignment") public Assignment initAssignment()
  { 
    return getInstance();
  }
}