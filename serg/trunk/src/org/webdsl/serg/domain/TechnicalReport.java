package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class TechnicalReport extends Publication 
{ 
  public TechnicalReport () 
  { }

  private int number = 0;

  public int getNumber()
  { 
    return number;
  }

  public void setNumber(int number)
  { 
    this.number = number;
  }

  private @Column(length = 1000000) String document = "";

  public String getDocument()
  { 
    return document;
  }

  public void setDocument(String document)
  { 
    this.document = document;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Publication preprintof;

  public Publication getPreprintof()
  { 
    return preprintof;
  }

  public void setPreprintof(Publication preprintof)
  { 
    this.preprintof = preprintof;
  }

  public String getName()
  { 
    return getId().toString();
  }
}