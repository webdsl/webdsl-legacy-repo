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

  private String email;

  public String getEmail()
  { 
    return email;
  }

  public void setEmail(String email)
  { 
    this.email = email;
  }

  private String homepage;

  public String getHomepage()
  { 
    return homepage;
  }

  public void setHomepage(String homepage)
  { 
    this.homepage = homepage;
  }

  private String photo;

  public String getPhoto()
  { 
    return photo;
  }

  public void setPhoto(String photo)
  { 
    this.photo = photo;
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private User user;

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