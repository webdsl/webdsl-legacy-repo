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

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private java.util.List<Presentation> _talks = new java.util.ArrayList<Presentation>();

  public java.util.List<Presentation> getTalks()
  { 
    return _talks;
  }

  public void setTalks(java.util.List<Presentation> _talks)
  { 
    this._talks = _talks;
  }

  public java.util.List<Presentation> getTalksList()
  { 
    return new ArrayList(getTalks());
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
}