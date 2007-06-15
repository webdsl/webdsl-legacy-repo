package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Conference  
{ 
  public Conference () 
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

  private String _booktitle = "";

  public String getBooktitle()
  { 
    return _booktitle;
  }

  public void setBooktitle(String _booktitle)
  { 
    this._booktitle = _booktitle;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<Person> _editors = new java.util.ArrayList<Person>();

  public java.util.List<Person> getEditors()
  { 
    return _editors;
  }

  public void setEditors(java.util.List<Person> _editors)
  { 
    this._editors = _editors;
  }

  public java.util.List<Person> getEditorsList()
  { 
    return new ArrayList(getEditors());
  }

  private String _place = "";

  public String getPlace()
  { 
    return _place;
  }

  public void setPlace(String _place)
  { 
    this._place = _place;
  }

  private int _year = 0;

  public int getYear()
  { 
    return _year;
  }

  public void setYear(int _year)
  { 
    this._year = _year;
  }

  private String _month = "";

  public String getMonth()
  { 
    return _month;
  }

  public void setMonth(String _month)
  { 
    this._month = _month;
  }

  private String _url = "";

  public String getUrl()
  { 
    return _url;
  }

  public void setUrl(String _url)
  { 
    this._url = _url;
  }

  private int _acceptance = 0;

  public int getAcceptance()
  { 
    return _acceptance;
  }

  public void setAcceptance(int _acceptance)
  { 
    this._acceptance = _acceptance;
  }

  public String getName()
  { 
    return getAcronym().toString();
  }
}