package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Person  
{ 
  public Person () 
  { }

  @Id @GeneratedValue private Long id;

  private String fullname;

  public String getFullname()
  { 
    return fullname;
  }

  public void setFullname(String fullname)
  { 
    this.fullname = fullname;
  }

  @ManyToMany() @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Map<String, Address> addresses = new HashMap<String, Address>();

  public Map<String, Address> getAddresses()
  { 
    return addresses;
  }

  public void setAddresses(Map<String, Address> addresses)
  { 
    this.addresses = addresses;
  }

  public void putAddresses(String key, Address value)
  { 
    this.addresses.put(key, value);
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<URL> homepages = new HashSet<URL>();

  public Set<URL> getHomepages()
  { 
    return homepages;
  }

  public void setHomepages(Set<URL> homepages)
  { 
    this.homepages = homepages;
  }

  public void addHomepages(URL a_0)
  { 
    this.homepages.add(a_0);
  }

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