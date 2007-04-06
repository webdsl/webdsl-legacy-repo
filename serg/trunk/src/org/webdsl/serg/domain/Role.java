package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Role  
{ 
  public Role () 
  { }

  @Id @GeneratedValue private Long id;

  private User user;

  public User getUser()
  { 
    return user;
  }

  public void setUser(User user)
  { 
    this.user = user;
  }
}