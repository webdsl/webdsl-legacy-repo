package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("studentCourseHome") public class StudentCourseHome extends EntityHome<StudentCourse> 
{ 
  @Factory("studentCourse") public StudentCourse initStudentCourse()
  { 
    return getInstance();
  }
}