package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("studentProjectHome") public class StudentProjectHome extends EntityHome<StudentProject> 
{ 
  @Factory("studentProject") public StudentProject initStudentProject()
  { 
    return getInstance();
  }
}