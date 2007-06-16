package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Shop  
{ 
  public Shop () 
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

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private java.util.List<Cart> _carts = new java.util.ArrayList<Cart>();

  public java.util.List<Cart> getCarts()
  { 
    return _carts;
  }

  public void setCarts(java.util.List<Cart> _carts)
  { 
    this._carts = _carts;
  }

  public java.util.List<Cart> getCartsList()
  { 
    return new ArrayList(getCarts());
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Product _first = null;

  public Product getFirst()
  { 
    return _first;
  }

  public void setFirst(Product _first)
  { 
    this._first = _first;
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Product _last = null;

  public Product getLast()
  { 
    return _last;
  }

  public void setLast(Product _last)
  { 
    this._last = _last;
  }
}