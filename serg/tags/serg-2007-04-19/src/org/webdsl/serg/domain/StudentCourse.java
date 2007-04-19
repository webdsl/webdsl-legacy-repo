package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class StudentCourse  
{ 
  public StudentCourse () 
  { }

  @Id @GeneratedValue private Long id;

  public Long getId()
  { 
    return id;
  }

  private void setId(Long id)
  { 
    this.id = id;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Course course;

  public Course getCourse()
  { 
    return course;
  }

  public void setCourse(Course course)
  { 
    this.course = course;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person student;

  public Person getStudent()
  { 
    return student;
  }

  public void setStudent(Person student)
  { 
    this.student = student;
  }

  private Date exam;

  public Date getExam()
  { 
    return exam;
  }

  public void setExam(Date exam)
  { 
    this.exam = exam;
  }

  private int grade;

  public int getGrade()
  { 
    return grade;
  }

  public void setGrade(int grade)
  { 
    this.grade = grade;
  }

  public String getName()
  { 
    return getId().toString();
  }
}