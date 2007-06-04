package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class User  
{ 
  public User () 
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

  private String _username = "";

  public String getUsername()
  { 
    return _username;
  }

  public void setUsername(String _username)
  { 
    this._username = _username;
  }

  private String _password = "";

  public String getPassword()
  { 
    return _password;
  }

  public void setPassword(String _password)
  { 
    this._password = _password;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person _person = null;

  public Person getPerson()
  { 
    return _person;
  }

  public void setPerson(Person _person)
  { 
    this._person = _person;
  }

  public String getName()
  { 
    return getUsername().toString();
  }
}