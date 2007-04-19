package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class URL  
{ 
  public URL () 
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

  private String url;

  public String getUrl()
  { 
    return url;
  }

  public void setUrl(String url)
  { 
    this.url = url;
  }

  public String getName()
  { 
    return getId().toString();
  }
}