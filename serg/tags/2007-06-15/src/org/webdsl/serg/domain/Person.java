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

  private String _fullname = "";

  public String getFullname()
  { 
    return _fullname;
  }

  public void setFullname(String _fullname)
  { 
    this._fullname = _fullname;
  }

  private String _email = "";

  public String getEmail()
  { 
    return _email;
  }

  public void setEmail(String _email)
  { 
    this._email = _email;
  }

  private String _homepage = "";

  public String getHomepage()
  { 
    return _homepage;
  }

  public void setHomepage(String _homepage)
  { 
    this._homepage = _homepage;
  }

  private String _photo = "";

  public String getPhoto()
  { 
    return _photo;
  }

  public void setPhoto(String _photo)
  { 
    this._photo = _photo;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private Address _address = new Address();

  public Address getAddress()
  { 
    return _address;
  }

  public void setAddress(Address _address)
  { 
    this._address = _address;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private User _user = null;

  public User getUser()
  { 
    return _user;
  }

  public void setUser(User _user)
  { 
    this._user = _user;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Blog _blog = null;

  public Blog getBlog()
  { 
    return _blog;
  }

  public void setBlog(Blog _blog)
  { 
    this._blog = _blog;
  }

  public String getName()
  { 
    return getFullname().toString();
  }
}