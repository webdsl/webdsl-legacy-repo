package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Supervisor extends Role 
{ 
  public Supervisor () 
  { }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Student> students = new HashSet<Student>();

  public Set<Student> getStudents()
  { 
    return students;
  }

  public void setStudents(Set<Student> students)
  { 
    this.students = students;
  }

  public void addStudents(Student d_0)
  { 
    this.students.add(d_0);
  }

  public String getName()
  { 
    return getId().toString();
  }
}