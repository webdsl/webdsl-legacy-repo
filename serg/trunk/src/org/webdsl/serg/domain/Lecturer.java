package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Lecturer extends Role 
{ 
  public Lecturer () 
  { }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Course> courses = new HashSet<Course>();

  public Set<Course> getCourses()
  { 
    return courses;
  }

  public void setCourses(Set<Course> courses)
  { 
    this.courses = courses;
  }

  public void addCourses(Course c_0)
  { 
    this.courses.add(c_0);
  }

  public String getName()
  { 
    return getId().toString();
  }
}