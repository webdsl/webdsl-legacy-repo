package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("supervisorHome") public class SupervisorHome extends EntityHome<Supervisor> 
{ 
  @Factory("supervisor") public Supervisor initSupervisor()
  { 
    return getInstance();
  }
}