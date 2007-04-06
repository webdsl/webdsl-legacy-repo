package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class EduUnit  
{ 
  public EduUnit () 
  { }

  @Id @GeneratedValue private Long id;

  private String title;

  public String getTitle()
  { 
    return title;
  }

  public void setTitle(String title)
  { 
    this.title = title;
  }

  private String code;

  public String getCode()
  { 
    return code;
  }

  public void setCode(String code)
  { 
    this.code = code;
  }

  private int credits;

  public int getCredits()
  { 
    return credits;
  }

  public void setCredits(int credits)
  { 
    this.credits = credits;
  }
}