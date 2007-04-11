package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("startedHome") public class StartedHome extends EntityHome<Started> 
{ 
  @Factory("started") public Started initStarted()
  { 
    return getInstance();
  }
}