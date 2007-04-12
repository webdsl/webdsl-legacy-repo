package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Course  
{ 
  public Course () 
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

  @ManyToOne @JoinColumn(name = "CourseUnit") private EduUnit unit;

  public EduUnit getUnit()
  { 
    return unit;
  }

  public void setUnit(EduUnit unit)
  { 
    this.unit = unit;
  }

  private int year;

  public int getYear()
  { 
    return year;
  }

  public void setYear(int year)
  { 
    this.year = year;
  }

  private int period;

  public int getPeriod()
  { 
    return period;
  }

  public void setPeriod(int period)
  { 
    this.period = period;
  }

  @ManyToOne @JoinColumn(name = "CourseLecturer") private Person lecturer;

  public Person getLecturer()
  { 
    return lecturer;
  }

  public void setLecturer(Person lecturer)
  { 
    this.lecturer = lecturer;
  }
}