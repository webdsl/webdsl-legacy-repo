package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Product  
{ 
  public Product () 
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

  private String _name = "";

  public String getName()
  { 
    return _name;
  }

  public void setName(String _name)
  { 
    this._name = _name;
  }

  private int _price = 0;

  public int getPrice()
  { 
    return _price;
  }

  public void setPrice(int _price)
  { 
    this._price = _price;
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Shop _shop = null;

  public Shop getShop()
  { 
    return _shop;
  }

  public void setShop(Shop _shop)
  { 
    this._shop = _shop;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Product _previous = null;

  public Product getPrevious()
  { 
    return _previous;
  }

  public void setPrevious(Product _previous)
  { 
    this._previous = _previous;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Product _next = null;

  public Product getNext()
  { 
    return _next;
  }

  public void setNext(Product _next)
  { 
    this._next = _next;
  }
}