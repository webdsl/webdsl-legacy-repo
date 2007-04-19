package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("eduUnitHome") public class EduUnitHome extends EntityHome<EduUnit> 
{ 
  @Factory("eduUnit") public EduUnit initEduUnit()
  { 
    return getInstance();
  }
}