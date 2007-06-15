package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Journal  
{ 
  public Journal () 
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

  private String _fullname = "";

  public String getFullname()
  { 
    return _fullname;
  }

  public void setFullname(String _fullname)
  { 
    this._fullname = _fullname;
  }

  private String _acronym = "";

  public String getAcronym()
  { 
    return _acronym;
  }

  public void setAcronym(String _acronym)
  { 
    this._acronym = _acronym;
  }

  public String getName()
  { 
    return getAcronym().toString();
  }
}