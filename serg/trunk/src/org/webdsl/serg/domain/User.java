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

  private String email;

  public String getEmail()
  { 
    return email;
  }

  public void setEmail(String email)
  { 
    this.email = email;
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

  @ManyToOne @JoinColumn(name = "UserPerson") private Person person;

  public Person getPerson()
  { 
    return person;
  }

  public void setPerson(Person person)
  { 
    this.person = person;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Role> role = new HashSet<Role>();

  public Set<Role> getRole()
  { 
    return role;
  }

  public void setRole(Set<Role> role)
  { 
    this.role = role;
  }

  public void addRole(Role b_0)
  { 
    this.role.add(b_0);
  }

  public String getName()
  { 
    return getUsername().toString();
  }
}