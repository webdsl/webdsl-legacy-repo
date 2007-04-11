package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("masterStatusHome") public class MasterStatusHome extends EntityHome<MasterStatus> 
{ 
  @Factory("masterStatus") public MasterStatus initMasterStatus()
  { 
    return getInstance();
  }
}