package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Project  
{ 
  public Project () 
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

  @ManyToOne @JoinColumn(name = "ProjectUnit") private EduUnit unit;

  public EduUnit getUnit()
  { 
    return unit;
  }

  public void setUnit(EduUnit unit)
  { 
    this.unit = unit;
  }

  private String topic;

  public String getTopic()
  { 
    return topic;
  }

  public void setTopic(String topic)
  { 
    this.topic = topic;
  }

  private String description;

  public String getDescription()
  { 
    return description;
  }

  public void setDescription(String description)
  { 
    this.description = description;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Person> student = new HashSet<Person>();

  public Set<Person> getStudent()
  { 
    return student;
  }

  public void setStudent(Set<Person> student)
  { 
    this.student = student;
  }

  public void addStudent(Person e_0)
  { 
    this.student.add(e_0);
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Person> supervisor = new HashSet<Person>();

  public Set<Person> getSupervisor()
  { 
    return supervisor;
  }

  public void setSupervisor(Set<Person> supervisor)
  { 
    this.supervisor = supervisor;
  }

  public void addSupervisor(Person f_0)
  { 
    this.supervisor.add(f_0);
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

  private Date finish;

  public Date getFinish()
  { 
    return finish;
  }

  public void setFinish(Date finish)
  { 
    this.finish = finish;
  }

  public String getName()
  { 
    return getId().toString();
  }
}