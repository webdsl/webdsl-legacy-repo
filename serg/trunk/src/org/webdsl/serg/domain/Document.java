package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Document  
{ 
  public Document () 
  { }

  @Id @GeneratedValue private Long id;

  private String doc;

  public String getDoc()
  { 
    return doc;
  }

  public void setDoc(String doc)
  { 
    this.doc = doc;
  }

  private Date date;

  public Date getDate()
  { 
    return date;
  }

  public void setDate(Date date)
  { 
    this.date = date;
  }

  private String comments;

  public String getComments()
  { 
    return comments;
  }

  public void setComments(String comments)
  { 
    this.comments = comments;
  }
}