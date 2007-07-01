package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.faces.event.ValueChangeEvent;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.ejb.Remove;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Factory;
import org.webdsl.serg.domain.*;

@Stateful @Name("editCart") public class EditCartBean  implements EditCartBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editCart" + ".initalize()");
    if(cartId == null)
    { 
      log.info("No " + "cartId" + " defined, creating new " + "Cart");
      cart = new Cart();
    }
    else
    { 
      cart = em.find(Cart.class, cartId);
    }
    initPerson255List();
    initProduct29List();
    initPerson143List();
    initProject134List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("cart") private Long cartId;

  private Cart cart;

  public void setCart(Cart cart)
  { 
    log.info("setCart");
    this.cart = cart;
  }

  public Cart getCart()
  { 
    log.info("getCart");
    return cart;
  }

  public void setPerson17(Person person256)
  { 
    cart.setShopper(person256);
  }

  public void removeProduct2(Product product28)
  { 
    this.getCart().getProducts().remove(product28);
  }

  public void addProduct2(Product product28)
  { 
    this.getCart().getProducts().add(product28);
  }

  @End public String cancel()
  { 
    return "/" + "viewCart" + ".seam?" + ("cart" + "=" + cart.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getCart());
    return "/" + "viewCart" + ".seam?" + ("cart" + "=" + cart.getId() + "");
  }

  private String newPerson255;

  public void setNewPerson255(String p)
  { 
    newPerson255 = p;
  }

  public String getNewPerson255()
  { 
    return newPerson255;
  }

  public void selectPerson255(ValueChangeEvent event)
  { 
    log.info("selectPerson255" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person255 = em.find(Person.class, id);
      setPerson17(person255);
    }
  }

  @DataModel("person255List") private Map<String, String> person255List;

  public Map<String, String> getPerson255List()
  { 
    return person255List;
  }

  @Factory("person255List") public void initPerson255List()
  { 
    log.info("initPerson255List");
    person255List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person255List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct29;

  public void setNewProduct29(String p)
  { 
    newProduct29 = p;
  }

  public String getNewProduct29()
  { 
    return newProduct29;
  }

  public void selectProduct29(ValueChangeEvent event)
  { 
    log.info("selectProduct29" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Product product29 = em.find(Product.class, id);
      addProduct2(product29);
    }
  }

  @DataModel("product29List") private Map<String, String> product29List;

  public Map<String, String> getProduct29List()
  { 
    return product29List;
  }

  @Factory("product29List") public void initProduct29List()
  { 
    log.info("initProduct29List");
    product29List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product29List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person143List") private List<Person> person143List;

  public List<Person> getPerson143List()
  { 
    log.info("getPerson143List");
    return person143List;
  }

  @Factory("person143List") public void initPerson143List()
  { 
    log.info("initPerson143List");
    person143List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project134List") private List<ResearchProject> project134List;

  public List<ResearchProject> getProject134List()
  { 
    log.info("getProject134List");
    return project134List;
  }

  @Factory("project134List") public void initProject134List()
  { 
    log.info("initProject134List");
    project134List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}