package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Issue  
{ 
  public Issue () 
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

  @Column(length = 1000000) private String _description = "";

  public String getDescription()
  { 
    return _description;
  }

  public void setDescription(String _description)
  { 
    this._description = _description;
  }

  private java.util.Date _due = new java.util.Date();

  public java.util.Date getDue()
  { 
    return _due;
  }

  public void setDue(java.util.Date _due)
  { 
    this._due = _due;
  }

  private int _priority = 0;

  public int getPriority()
  { 
    return _priority;
  }

  public void setPriority(int _priority)
  { 
    this._priority = _priority;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private java.util.Set<Issue> _issues = new java.util.HashSet<Issue>();

  public java.util.Set<Issue> getIssues()
  { 
    return _issues;
  }

  public void setIssues(java.util.Set<Issue> _issues)
  { 
    this._issues = _issues;
  }

  public java.util.List<Issue> getIssuesList()
  { 
    return new ArrayList(getIssues());
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.Set<Person> _assigned = new java.util.HashSet<Person>();

  public java.util.Set<Person> getAssigned()
  { 
    return _assigned;
  }

  public void setAssigned(java.util.Set<Person> _assigned)
  { 
    this._assigned = _assigned;
  }

  public java.util.List<Person> getAssignedList()
  { 
    return new ArrayList(getAssigned());
  }

  private String _status = "";

  public String getStatus()
  { 
    return _status;
  }

  public void setStatus(String _status)
  { 
    this._status = _status;
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}