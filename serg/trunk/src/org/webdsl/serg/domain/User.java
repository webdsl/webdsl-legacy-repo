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

  private String username;

  public String getUsername()
  { 
    return username;
  }

  public void setUsername(String username)
  { 
    this.username = username;
  }

  private String password;

  public String getPassword()
  { 
    return password;
  }

  public void setPassword(String password)
  { 
    this.password = password;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person person;

  public Person getPerson()
  { 
    return person;
  }

  public void setPerson(Person person)
  { 
    this.person = person;
  }

  public String getName()
  { 
    return getUsername().toString();
  }
}