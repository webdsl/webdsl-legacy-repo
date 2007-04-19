package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("roleHome") public class RoleHome extends EntityHome<Role> 
{ 
  @Factory("role") public Role initRole()
  { 
    return getInstance();
  }
}