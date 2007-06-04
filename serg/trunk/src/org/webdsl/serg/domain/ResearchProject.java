package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class ResearchProject  
{ 
  public ResearchProject () 
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

  private String _fullname = "";

  public String getFullname()
  { 
    return _fullname;
  }

  public void setFullname(String _fullname)
  { 
    this._fullname = _fullname;
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

  @Column(length = 1000000) private String _description = "";

  public String getDescription()
  { 
    return _description;
  }

  public void setDescription(String _description)
  { 
    this._description = _description;
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Publication _proposal = null;

  public Publication getProposal()
  { 
    return _proposal;
  }

  public void setProposal(Publication _proposal)
  { 
    this._proposal = _proposal;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.Set<Publication> _publications = new java.util.HashSet<Publication>();

  public java.util.Set<Publication> getPublications()
  { 
    return _publications;
  }

  public void setPublications(java.util.Set<Publication> _publications)
  { 
    this._publications = _publications;
  }

  public java.util.List<Publication> getPublicationsList()
  { 
    return new ArrayList(getPublications());
  }

  public String getName()
  { 
    return getAcronym().toString();
  }
}