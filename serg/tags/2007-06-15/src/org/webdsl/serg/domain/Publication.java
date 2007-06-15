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

  private String _title = "";

  public String getTitle()
  { 
    return _title;
  }

  public void setTitle(String _title)
  { 
    this._title = _title;
  }

  private String _subtitle = "";

  public String getSubtitle()
  { 
    return _subtitle;
  }

  public void setSubtitle(String _subtitle)
  { 
    this._subtitle = _subtitle;
  }

  private int _year = 0;

  public int getYear()
  { 
    return _year;
  }

  public void setYear(int _year)
  { 
    this._year = _year;
  }

  private String _pdf = "";

  public String getPdf()
  { 
    return _pdf;
  }

  public void setPdf(String _pdf)
  { 
    this._pdf = _pdf;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<Person> _authors = new java.util.ArrayList<Person>();

  public java.util.List<Person> getAuthors()
  { 
    return _authors;
  }

  public void setAuthors(java.util.List<Person> _authors)
  { 
    this._authors = _authors;
  }

  public java.util.List<Person> getAuthorsList()
  { 
    return new ArrayList(getAuthors());
  }

  @Column(length = 1000000) private String _abstract = "";

  public String getAbstract()
  { 
    return _abstract;
  }

  public void setAbstract(String _abstract)
  { 
    this._abstract = _abstract;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.Set<ResearchProject> _projects = new java.util.HashSet<ResearchProject>();

  public java.util.Set<ResearchProject> getProjects()
  { 
    return _projects;
  }

  public void setProjects(java.util.Set<ResearchProject> _projects)
  { 
    this._projects = _projects;
  }

  public java.util.List<ResearchProject> getProjectsList()
  { 
    return new ArrayList(getProjects());
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}