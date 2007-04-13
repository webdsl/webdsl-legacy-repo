package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class TechnicalReport  
{ 
  public TechnicalReport () 
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

  private String title;

  public String getTitle()
  { 
    return title;
  }

  public void setTitle(String title)
  { 
    this.title = title;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private List<Person> authors = new LinkedList<Person>();

  public List<Person> getAuthors()
  { 
    return authors;
  }

  public void setAuthors(List<Person> authors)
  { 
    this.authors = authors;
  }

  public void addAuthors(Person j_0)
  { 
    this.authors.add(j_0);
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

  private int number;

  public int getNumber()
  { 
    return number;
  }

  public void setNumber(int number)
  { 
    this.number = number;
  }

  private String document;

  public String getDocument()
  { 
    return document;
  }

  public void setDocument(String document)
  { 
    this.document = document;
  }

  private String trabstract;

  public String getTrabstract()
  { 
    return trabstract;
  }

  public void setTrabstract(String trabstract)
  { 
    this.trabstract = trabstract;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<ResearchProject> project = new HashSet<ResearchProject>();

  public Set<ResearchProject> getProject()
  { 
    return project;
  }

  public void setProject(Set<ResearchProject> project)
  { 
    this.project = project;
  }

  public void addProject(ResearchProject k_0)
  { 
    this.project.add(k_0);
  }

  @ManyToOne @JoinColumn(name = "TechnicalReportPreprintof") private Publication preprintof;

  public Publication getPreprintof()
  { 
    return preprintof;
  }

  public void setPreprintof(Publication preprintof)
  { 
    this.preprintof = preprintof;
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}