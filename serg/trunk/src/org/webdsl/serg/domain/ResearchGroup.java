package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class ResearchGroup  
{ 
  public ResearchGroup () 
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

  private String _acronym = "";

  public String getAcronym()
  { 
    return _acronym;
  }

  public void setAcronym(String _acronym)
  { 
    this._acronym = _acronym;
  }

  private String _fullname = "";

  public String getFullname()
  { 
    return _fullname;
  }

  public void setFullname(String _fullname)
  { 
    this._fullname = _fullname;
  }

  @Column(length = 1000000) private String _mission = "";

  public String getMission()
  { 
    return _mission;
  }

  public void setMission(String _mission)
  { 
    this._mission = _mission;
  }

  private String _logo = "";

  public String getLogo()
  { 
    return _logo;
  }

  public void setLogo(String _logo)
  { 
    this._logo = _logo;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.Set<Person> _members = new java.util.HashSet<Person>();

  public java.util.Set<Person> getMembers()
  { 
    return _members;
  }

  public void setMembers(java.util.Set<Person> _members)
  { 
    this._members = _members;
  }

  public java.util.List<Person> getMembersList()
  { 
    return new ArrayList(getMembers());
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

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<News> _news = new java.util.ArrayList<News>();

  public java.util.List<News> getNews()
  { 
    return _news;
  }

  public void setNews(java.util.List<News> _news)
  { 
    this._news = _news;
  }

  public java.util.List<News> getNewsList()
  { 
    return new ArrayList(getNews());
  }

  public String getName()
  { 
    return getAcronym().toString();
  }
}