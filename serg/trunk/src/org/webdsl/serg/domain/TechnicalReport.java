package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class TechnicalReport extends Publication 
{ 
  public TechnicalReport () 
  { }

  private int _number = 0;

  public int getNumber()
  { 
    return _number;
  }

  public void setNumber(int _number)
  { 
    this._number = _number;
  }

  @Column(length = 1000000) private String _document = "";

  public String getDocument()
  { 
    return _document;
  }

  public void setDocument(String _document)
  { 
    this._document = _document;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Publication _preprintof = null;

  public Publication getPreprintof()
  { 
    return _preprintof;
  }

  public void setPreprintof(Publication _preprintof)
  { 
    this._preprintof = _preprintof;
  }

  public String getName()
  { 
    return getId().toString();
  }
}