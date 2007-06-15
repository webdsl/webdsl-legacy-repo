package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Colloquium  
{ 
  public Colloquium () 
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

  private String _name = "";

  public String getName()
  { 
    return _name;
  }

  public void setName(String _name)
  { 
    this._name = _name;
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person _contact = null;

  public Person getContact()
  { 
    return _contact;
  }

  public void setContact(Person _contact)
  { 
    this._contact = _contact;
  }

  private String _mailinglist = "";

  public String getMailinglist()
  { 
    return _mailinglist;
  }

  public void setMailinglist(String _mailinglist)
  { 
    this._mailinglist = _mailinglist;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private ResearchGroup _group = null;

  public ResearchGroup getGroup()
  { 
    return _group;
  }

  public void setGroup(ResearchGroup _group)
  { 
    this._group = _group;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<ResearchProject> _projects = new java.util.ArrayList<ResearchProject>();

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

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private java.util.List<Presentation> _presentations = new java.util.ArrayList<Presentation>();

  public java.util.List<Presentation> getPresentations()
  { 
    return _presentations;
  }

  public void setPresentations(java.util.List<Presentation> _presentations)
  { 
    this._presentations = _presentations;
  }

  public java.util.List<Presentation> getPresentationsList()
  { 
    return new ArrayList(getPresentations());
  }
}