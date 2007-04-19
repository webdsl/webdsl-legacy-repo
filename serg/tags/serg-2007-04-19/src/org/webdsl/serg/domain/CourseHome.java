package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("courseHome") public class CourseHome extends EntityHome<Course> 
{ 
  @Factory("course") public Course initCourse()
  { 
    return getInstance();
  }
}