package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("lecturerHome") public class LecturerHome extends EntityHome<Lecturer> 
{ 
  @Factory("lecturer") public Lecturer initLecturer()
  { 
    return getInstance();
  }
}