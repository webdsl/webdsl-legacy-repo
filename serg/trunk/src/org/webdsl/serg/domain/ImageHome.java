package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("imageHome") public class ImageHome extends EntityHome<Image> 
{ 
  @Factory("image") public Image initImage()
  { 
    return getInstance();
  }
}