package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("adminHome") public class AdminHome extends EntityHome<Admin> 
{ 
  @Factory("admin") public Admin initAdmin()
  { 
    return getInstance();
  }
}