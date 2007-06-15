package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Post  
{ 
  public Post () 
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

  private String _subject = "";

  public String getSubject()
  { 
    return _subject;
  }

  public void setSubject(String _subject)
  { 
    this._subject = _subject;
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

  private java.util.Date _posted = new java.util.Date();

  public java.util.Date getPosted()
  { 
    return _posted;
  }

  public void setPosted(java.util.Date _posted)
  { 
    this._posted = _posted;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Discussion _discussion = null;

  public Discussion getDiscussion()
  { 
    return _discussion;
  }

  public void setDiscussion(Discussion _discussion)
  { 
    this._discussion = _discussion;
  }

  @Column(length = 1000000) private String _text = "";

  public String getText()
  { 
    return _text;
  }

  public void setText(String _text)
  { 
    this._text = _text;
  }

  public String getName()
  { 
    return getSubject().toString();
  }
}