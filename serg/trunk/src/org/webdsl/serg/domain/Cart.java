package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Cart  
{ 
  public Cart () 
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

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Person _shopper = null;

  public Person getShopper()
  { 
    return _shopper;
  }

  public void setShopper(Person _shopper)
  { 
    this._shopper = _shopper;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<Product> _products = new java.util.ArrayList<Product>();

  public java.util.List<Product> getProducts()
  { 
    return _products;
  }

  public void setProducts(java.util.List<Product> _products)
  { 
    this._products = _products;
  }

  public java.util.List<Product> getProductsList()
  { 
    return new ArrayList(getProducts());
  }

  public String getName()
  { 
    return getId().toString();
  }
}