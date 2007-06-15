package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Forum  
{ 
  public Forum () 
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

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<Discussion> _discussions = new java.util.ArrayList<Discussion>();

  public java.util.List<Discussion> getDiscussions()
  { 
    return _discussions;
  }

  public void setDiscussions(java.util.List<Discussion> _discussions)
  { 
    this._discussions = _discussions;
  }

  public java.util.List<Discussion> getDiscussionsList()
  { 
    return new ArrayList(getDiscussions());
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}