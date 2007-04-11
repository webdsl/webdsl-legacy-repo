package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("studentMasterHome") public class StudentMasterHome extends EntityHome<StudentMaster> 
{ 
  @Factory("studentMaster") public StudentMaster initStudentMaster()
  { 
    return getInstance();
  }
}