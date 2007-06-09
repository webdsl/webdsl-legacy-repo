package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Blog  
{ 
  public Blog () 
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person _author = null;

  public Person getAuthor()
  { 
    return _author;
  }

  public void setAuthor(Person _author)
  { 
    this._author = _author;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private java.util.List<BlogEntry> _entries = new java.util.ArrayList<BlogEntry>();

  public java.util.List<BlogEntry> getEntries()
  { 
    return _entries;
  }

  public void setEntries(java.util.List<BlogEntry> _entries)
  { 
    this._entries = _entries;
  }

  public java.util.List<BlogEntry> getEntriesList()
  { 
    return new ArrayList(getEntries());
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<Category> _categories = new java.util.ArrayList<Category>();

  public java.util.List<Category> getCategories()
  { 
    return _categories;
  }

  public void setCategories(java.util.List<Category> _categories)
  { 
    this._categories = _categories;
  }

  public java.util.List<Category> getCategoriesList()
  { 
    return new ArrayList(getCategories());
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}