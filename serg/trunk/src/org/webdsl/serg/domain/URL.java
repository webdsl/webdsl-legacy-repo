package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class URL  
{ 
  public URL () 
  { }

  @Id @GeneratedValue private Long id;

  private String url;

  public String getUrl()
  { 
    return url;
  }

  public void setUrl(String url)
  { 
    this.url = url;
  }
}