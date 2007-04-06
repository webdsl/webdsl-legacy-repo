package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Student extends Role 
{ 
  public Student () 
  { }

  @Id @GeneratedValue private Long id;

  private String number;

  public String getNumber()
  { 
    return number;
  }

  public void setNumber(String number)
  { 
    this.number = number;
  }

  private int year;

  public int getYear()
  { 
    return year;
  }

  public void setYear(int year)
  { 
    this.year = year;
  }
}