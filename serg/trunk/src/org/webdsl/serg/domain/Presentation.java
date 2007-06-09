package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Presentation  
{ 
  public Presentation () 
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person _speaker = null;

  public Person getSpeaker()
  { 
    return _speaker;
  }

  public void setSpeaker(Person _speaker)
  { 
    this._speaker = _speaker;
  }

  private java.util.Date _date = new java.util.Date();

  public java.util.Date getDate()
  { 
    return _date;
  }

  public void setDate(java.util.Date _date)
  { 
    this._date = _date;
  }

  private java.util.Date _time = new java.util.Date();

  public java.util.Date getTime()
  { 
    return _time;
  }

  public void setTime(java.util.Date _time)
  { 
    this._time = _time;
  }

  private java.util.Date _end = new java.util.Date();

  public java.util.Date getEnd()
  { 
    return _end;
  }

  public void setEnd(java.util.Date _end)
  { 
    this._end = _end;
  }

  private String _venue = "";

  public String getVenue()
  { 
    return _venue;
  }

  public void setVenue(String _venue)
  { 
    this._venue = _venue;
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

  @ManyToMany private java.util.List<ResearchProject> _projects = new java.util.ArrayList<ResearchProject>();

  public java.util.List<ResearchProject> getProjects()
  { 
    return _projects;
  }

  public void setProjects(java.util.List<ResearchProject> _projects)
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