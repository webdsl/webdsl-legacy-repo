package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("userHome") public class UserHome extends EntityHome<User> 
{ 
  @Factory("user") public User initUser()
  { 
    return getInstance();
  }
}