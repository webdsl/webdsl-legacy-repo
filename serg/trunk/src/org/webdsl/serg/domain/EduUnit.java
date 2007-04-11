package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class EduUnit  
{ 
  public EduUnit () 
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