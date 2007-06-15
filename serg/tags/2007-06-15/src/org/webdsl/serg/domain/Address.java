package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Address  
{ 
  public Address () 
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

  private String _street = "";

  public String getStreet()
  { 
    return _street;
  }

  public void setStreet(String _street)
  { 
    this._street = _street;
  }

  private String _city = "";

  public String getCity()
  { 
    return _city;
  }

  public void setCity(String _city)
  { 
    this._city = _city;
  }

  private String _phone = "";

  public String getPhone()
  { 
    return _phone;
  }

  public void setPhone(String _phone)
  { 
    this._phone = _phone;
  }

  public String getName()
  { 
    return getId().toString();
  }
}