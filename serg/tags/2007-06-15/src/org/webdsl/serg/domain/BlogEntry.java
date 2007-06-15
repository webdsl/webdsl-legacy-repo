package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class BlogEntry  
{ 
  public BlogEntry () 
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Blog _blog = null;

  public Blog getBlog()
  { 
    return _blog;
  }

  public void setBlog(Blog _blog)
  { 
    this._blog = _blog;
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

  private java.util.Date _created = new java.util.Date();

  public java.util.Date getCreated()
  { 
    return _created;
  }

  public void setCreated(java.util.Date _created)
  { 
    this._created = _created;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Category _category = null;

  public Category getCategory()
  { 
    return _category;
  }

  public void setCategory(Category _category)
  { 
    this._category = _category;
  }

  @Column(length = 1000000) private String _intro = "";

  public String getIntro()
  { 
    return _intro;
  }

  public void setIntro(String _intro)
  { 
    this._intro = _intro;
  }

  @Column(length = 1000000) private String _body = "";

  public String getBody()
  { 
    return _body;
  }

  public void setBody(String _body)
  { 
    this._body = _body;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private java.util.List<BlogComment> _comments = new java.util.ArrayList<BlogComment>();

  public java.util.List<BlogComment> getComments()
  { 
    return _comments;
  }

  public void setComments(java.util.List<BlogComment> _comments)
  { 
    this._comments = _comments;
  }

  public java.util.List<BlogComment> getCommentsList()
  { 
    return new ArrayList(getComments());
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}