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

  private String street;

  public String getStreet()
  { 
    return street;
  }

  public void setStreet(String street)
  { 
    this.street = street;
  }

  private String city;

  public String getCity()
  { 
    return city;
  }

  public void setCity(String city)
  { 
    this.city = city;
  }

  private String phone;

  public String getPhone()
  { 
    return phone;
  }

  public void setPhone(String phone)
  { 
    this.phone = phone;
  }

  public String getName()
  { 
    return getId().toString();
  }
}