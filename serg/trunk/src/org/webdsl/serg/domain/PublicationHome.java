package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("publicationHome") public class PublicationHome extends EntityHome<Publication> 
{ 
  @Factory("publication") public Publication initPublication()
  { 
    return getInstance();
  }
}