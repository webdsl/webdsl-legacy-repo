package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Category  
{ 
  public Category () 
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

  private String _name = "";

  public String getName()
  { 
    return _name;
  }

  public void setName(String _name)
  { 
    this._name = _name;
  }
}