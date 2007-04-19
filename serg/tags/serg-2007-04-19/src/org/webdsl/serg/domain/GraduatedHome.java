package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("graduatedHome") public class GraduatedHome extends EntityHome<Graduated> 
{ 
  @Factory("graduated") public Graduated initGraduated()
  { 
    return getInstance();
  }
}