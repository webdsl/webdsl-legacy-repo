package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class BlogComment  
{ 
  public BlogComment () 
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person _author = null;

  public Person getAuthor()
  { 
    return _author;
  }

  public void setAuthor(Person _author)
  { 
    this._author = _author;
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
    return getId().toString();
  }
}