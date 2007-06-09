package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class News  
{ 
  public News () 
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

  @Column(length = 1000000) private String _text = "";

  public String getText()
  { 
    return _text;
  }

  public void setText(String _text)
  { 
    this._text = _text;
  }

  private java.util.Date _date = new java.util.Date();

  public java.util.Date getDate()
  { 
    return _date;
  }

  public void setDate(java.util.Date _date)
  { 
    this._date = _date;
  }

  public String getName()
  { 
    return getId().toString();
  }
}