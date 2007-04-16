package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Person  
{ 
  public Person () 
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

  private String fullname;

  public String getFullname()
  { 
    return fullname;
  }

  public void setFullname(String fullname)
  { 
    this.fullname = fullname;
  }

  @ManyToOne @JoinColumn(name = "PersonAddress") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private Address address = new Address();

  public Address getAddress()
  { 
    return address;
  }

  public void setAddress(Address address)
  { 
    this.address = address;
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

  @org.hibernate.annotations.CollectionOfElements(targetElement = String.class) @JoinTable(name = "Person_Homepages") @Column(name = "homepages", nullable = false) @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private Set<String> homepages = new HashSet<String>();

  public Set<String> getHomepages()
  { 
    return homepages;
  }

  public void setHomepages(Set<String> homepages)
  { 
    this.homepages = homepages;
  }

  public void addHomepages(String a_0)
  { 
    this.homepages.add(a_0);
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private User user;

  public User getUser()
  { 
    return user;
  }

  public void setUser(User user)
  { 
    this.user = user;
  }

  public String getName()
  { 
    return getFullname().toString();
  }
}