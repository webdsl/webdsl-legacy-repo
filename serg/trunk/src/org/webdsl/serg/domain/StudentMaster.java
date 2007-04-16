package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class StudentMaster  
{ 
  public StudentMaster () 
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private User student;

  public User getStudent()
  { 
    return student;
  }

  public void setStudent(User student)
  { 
    this.student = student;
  }

  private String specialization;

  public String getSpecialization()
  { 
    return specialization;
  }

  public void setSpecialization(String specialization)
  { 
    this.specialization = specialization;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Course> courses = new HashSet<Course>();

  public Set<Course> getCourses()
  { 
    return courses;
  }

  public void setCourses(Set<Course> courses)
  { 
    this.courses = courses;
  }

  public void addCourses(Course e_0)
  { 
    this.courses.add(e_0);
  }

  @ManyToOne @JoinColumn(name = "StudentMasterResearch") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private ResearchAssignment research = new ResearchAssignment();

  public ResearchAssignment getResearch()
  { 
    return research;
  }

  public void setResearch(ResearchAssignment research)
  { 
    this.research = research;
  }

  @ManyToOne @JoinColumn(name = "StudentMasterThesis") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private ThesisProject thesis = new ThesisProject();

  public ThesisProject getThesis()
  { 
    return thesis;
  }

  public void setThesis(ThesisProject thesis)
  { 
    this.thesis = thesis;
  }

  private Date start;

  public Date getStart()
  { 
    return start;
  }

  public void setStart(Date start)
  { 
    this.start = start;
  }

  private Date ending;

  public Date getEnding()
  { 
    return ending;
  }

  public void setEnding(Date ending)
  { 
    this.ending = ending;
  }

  public String getName()
  { 
    return getId().toString();
  }
}