package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("thesisHome") public class ThesisHome extends EntityHome<Thesis> 
{ 
  @Factory("thesis") public Thesis initThesis()
  { 
    return getInstance();
  }
}