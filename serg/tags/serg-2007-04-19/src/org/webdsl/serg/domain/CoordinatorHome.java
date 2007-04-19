package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("coordinatorHome") public class CoordinatorHome extends EntityHome<Coordinator> 
{ 
  @Factory("coordinator") public Coordinator initCoordinator()
  { 
    return getInstance();
  }
}