package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Address  
{ 
  public Address () 
  { 
	  this.setCity("Delft");
	  this.setStreet("Mekelweg");
	  this.setNumber("4");
  }

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

  private String number;

  public String getNumber()
  { 
    return number;
  }

  public void setNumber(String number)
  { 
    this.number = number;
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

  public String getName()
  { 
    return getId().toString();
  }
}