package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Address  
{ 
  public Address () 
  { }

  @Id @GeneratedValue private Long id;

  private String street;

  public String getStreet()
  { 
    return street;
  }

  public void setStreet(String street)
  { 
    this.street = street;
  }
}