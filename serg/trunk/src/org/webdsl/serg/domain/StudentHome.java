package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("studentHome") public class StudentHome extends EntityHome<Student> 
{ 
  @Factory("student") public Student initStudent()
  { 
    return getInstance();
  }
}