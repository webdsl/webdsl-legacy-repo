package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Publication  
{ 
  public Publication () 
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

  private String title = "";

  public String getTitle()
  { 
    return title;
  }

  public void setTitle(String title)
  { 
    this.title = title;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private List<Person> authors = new java.util.ArrayList<Person>();

  public List<Person> getAuthors()
  { 
    return authors;
  }

  public List<Person> getAuthorsList()
  { 
    return authors;
  }

  public void setAuthors(List<Person> authors)
  { 
    this.authors = authors;
  }

  public void addAuthors(Person a_0)
  { 
    this.authors.add(a_0);
  }

  private int year = 0;

  public int getYear()
  { 
    return year;
  }

  public void setYear(int year)
  { 
    this.year = year;
  }

  private @Column(length = 1000000) String pubabstract = "";

  public String getPubabstract()
  { 
    return pubabstract;
  }

  public void setPubabstract(String pubabstract)
  { 
    this.pubabstract = pubabstract;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<ResearchProject> projects = new HashSet<ResearchProject>();

  public Set<ResearchProject> getProjects()
  { 
    return projects = new java.util.HashSet<ResearchProject>();
  }

  public List<ResearchProject> getProjectsList()
  { 
    return new ArrayList(projects);
  }

  public void setProjects(Set<ResearchProject> projects)
  { 
    this.projects = projects;
  }

  public void addProjects(ResearchProject b_0)
  { 
    this.projects.add(b_0);
  }

  private String pdf = "";

  public String getPdf()
  { 
    return pdf;
  }

  public void setPdf(String pdf)
  { 
    this.pdf = pdf;
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}