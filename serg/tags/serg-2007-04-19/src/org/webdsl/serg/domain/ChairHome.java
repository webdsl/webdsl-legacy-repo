package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("chairHome") public class ChairHome extends EntityHome<Chair> 
{ 
  @Factory("chair") public Chair initChair()
  { 
    return getInstance();
  }
}