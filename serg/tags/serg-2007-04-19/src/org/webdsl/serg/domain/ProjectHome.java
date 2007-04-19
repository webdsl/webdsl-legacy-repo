package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("projectHome") public class ProjectHome extends EntityHome<Project> 
{ 
  @Factory("project") public Project initProject()
  { 
    return getInstance();
  }
}