package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Discussion  
{ 
  public Discussion () 
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

  private String _topic = "";

  public String getTopic()
  { 
    return _topic;
  }

  public void setTopic(String _topic)
  { 
    this._topic = _topic;
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

  @ManyToOne private Forum _forum = new Forum();

  public Forum getForum()
  { 
    return _forum;
  }

  public void setForum(Forum _forum)
  { 
    this._forum = _forum;
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

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private java.util.List<Reply> _replies = new java.util.ArrayList<Reply>();

  public java.util.List<Reply> getReplies()
  { 
    return _replies;
  }

  public void setReplies(java.util.List<Reply> _replies)
  { 
    this._replies = _replies;
  }

  public java.util.List<Reply> getRepliesList()
  { 
    return new ArrayList(getReplies());
  }

  public String getName()
  { 
    return getTopic().toString();
  }
}