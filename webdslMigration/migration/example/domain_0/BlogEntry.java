package example.domain_0;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity @javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.SINGLE_TABLE) @javax.persistence.DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = javax.persistence.DiscriminatorType.STRING, length = 255) public class BlogEntry  implements Serializable
{ 
  public BlogEntry () 
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
    return o != null && o instanceof BlogEntry && ((BlogEntry)o).getId().equals(getId());
  }

  @ManyToOne(fetch = javax.persistence.FetchType.LAZY) @JoinColumn(name = "BlogEntryblog") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Blog _blog = null;

  public Blog getBlog()
  { 
    return _blog;
  }

  public void setBlog(Blog _blog)
  { 
    this._blog = _blog;
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
}