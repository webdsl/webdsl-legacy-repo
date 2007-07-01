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

@Stateful @Name("createShop") public class CreateShopBean  implements CreateShopBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createShop" + ".initalize()");
    Shop var87 = new Shop();
    shop = var87;
    initProduct15List();
    initCart11List();
    initProduct16List();
    initProduct18List();
    initPerson138List();
    initProject129List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeProduct1(Product product14)
  { 
    this.getShop().getProducts().remove(product14);
  }

  public void addProduct1(Product product14)
  { 
    this.getShop().getProducts().add(product14);
  }

  public void removeCart1(Cart cart9)
  { 
    this.getShop().getCarts().remove(cart9);
  }

  public void addCart1(Cart cart9)
  { 
    this.getShop().getCarts().add(cart9);
  }

  public void setProduct2(Product product17)
  { 
    shop.setFirst(product17);
  }

  public void setProduct3(Product product19)
  { 
    shop.setLast(product19);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getShop());
    return "/" + "viewShop" + ".seam?" + ("shop" + "=" + shop.getId() + "");
  }

  private String newProduct15;

  public void setNewProduct15(String p)
  { 
    newProduct15 = p;
  }

  public String getNewProduct15()
  { 
    return newProduct15;
  }

  public void selectProduct15(ValueChangeEvent event)
  { 
    log.info("selectProduct15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Product product15 = em.find(Product.class, id);
      addProduct1(product15);
    }
  }

  @DataModel("product15List") private Map<String, String> product15List;

  public Map<String, String> getProduct15List()
  { 
    return product15List;
  }

  @Factory("product15List") public void initProduct15List()
  { 
    log.info("initProduct15List");
    product15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product15List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCart11;

  public void setNewCart11(String p)
  { 
    newCart11 = p;
  }

  public String getNewCart11()
  { 
    return newCart11;
  }

  public void selectCart11(ValueChangeEvent event)
  { 
    log.info("selectCart11" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Cart cart11 = em.find(Cart.class, id);
      addCart1(cart11);
    }
  }

  @DataModel("cart11List") private Map<String, String> cart11List;

  public Map<String, String> getCart11List()
  { 
    return cart11List;
  }

  @Factory("cart11List") public void initCart11List()
  { 
    log.info("initCart11List");
    cart11List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Cart").getResultList())
    { 
      Cart p = (Cart)o;
      cart11List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct16;

  public void setNewProduct16(String p)
  { 
    newProduct16 = p;
  }

  public String getNewProduct16()
  { 
    return newProduct16;
  }

  public void selectProduct16(ValueChangeEvent event)
  { 
    log.info("selectProduct16" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Product product16 = em.find(Product.class, id);
      setProduct2(product16);
    }
  }

  @DataModel("product16List") private Map<String, String> product16List;

  public Map<String, String> getProduct16List()
  { 
    return product16List;
  }

  @Factory("product16List") public void initProduct16List()
  { 
    log.info("initProduct16List");
    product16List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product16List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct18;

  public void setNewProduct18(String p)
  { 
    newProduct18 = p;
  }

  public String getNewProduct18()
  { 
    return newProduct18;
  }

  public void selectProduct18(ValueChangeEvent event)
  { 
    log.info("selectProduct18" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Product product18 = em.find(Product.class, id);
      setProduct3(product18);
    }
  }

  @DataModel("product18List") private Map<String, String> product18List;

  public Map<String, String> getProduct18List()
  { 
    return product18List;
  }

  @Factory("product18List") public void initProduct18List()
  { 
    log.info("initProduct18List");
    product18List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product18List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person138List") private List<Person> person138List;

  public List<Person> getPerson138List()
  { 
    log.info("getPerson138List");
    return person138List;
  }

  @Factory("person138List") public void initPerson138List()
  { 
    log.info("initPerson138List");
    person138List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project129List") private List<ResearchProject> project129List;

  public List<ResearchProject> getProject129List()
  { 
    log.info("getProject129List");
    return project129List;
  }

  @Factory("project129List") public void initProject129List()
  { 
    log.info("initProject129List");
    project129List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Shop shop;

  public Shop getShop()
  { 
    log.info("getShop");
    return shop;
  }

  public void setShop(Shop shop)
  { 
    log.info("setShop");
    this.shop = shop;
  }
}