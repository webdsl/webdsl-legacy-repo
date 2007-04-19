package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("documentHome") public class DocumentHome extends EntityHome<Document> 
{ 
  @Factory("document") public Document initDocument()
  { 
    return getInstance();
  }
}