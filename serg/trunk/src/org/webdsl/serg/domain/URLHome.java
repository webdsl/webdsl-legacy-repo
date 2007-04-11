package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("uRLHome") public class URLHome extends EntityHome<URL> 
{ 
  @Factory("uRL") public URL initURL()
  { 
    return getInstance();
  }
}