package example.domain_0;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.SINGLE_TABLE) @javax.persistence.DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = javax.persistence.DiscriminatorType.STRING, length = 255) public class Blog  implements Serializable
{ 
  public Blog () 
  { }

  public String getId()
  { 
    return getKey();
  }

  public void setId(String val)
  { 
    setKey(val);
  }

  public boolean equals(Object o)
  { 
    return o != null && o instanceof Blog && ((Blog)o).getId().equals(getId());
  }

  @Id private String _key = "";

  public String getKey()
  { 
    return _key;
  }

  public void setKey(String _key)
  { 
    this._key = _key;
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
/*
  @ManyToMany(fetch = javax.persistence.FetchType.LAZY) @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<BlogEntry> _entries = new java.util.ArrayList<BlogEntry>();

  public java.util.List<BlogEntry> getEntries()
  { 
    return _entries;
  }

  public void setEntries(java.util.List<BlogEntry> _entries)
  { 
    this._entries = _entries;
    for(BlogEntry var1 : _entries)
    { 
      var1.setBlog(this);
    }
  }

  public java.util.List<BlogEntry> getEntriesList()
  { 
    return new ArrayList(getEntries());
  }

  public int getEntriesLength()
  { 
    return getEntries().size();
  }*/
}