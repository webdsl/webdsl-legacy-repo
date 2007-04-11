package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("thesisProjectHome") public class ThesisProjectHome extends EntityHome<ThesisProject> 
{ 
  @Factory("thesisProject") public ThesisProject initThesisProject()
  { 
    return getInstance();
  }
}