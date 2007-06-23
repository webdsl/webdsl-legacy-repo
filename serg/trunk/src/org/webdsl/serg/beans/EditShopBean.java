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

@Stateful @Name("editShop") public class EditShopBean  implements EditShopBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editShop" + ".initalize()");
    if(shopId == null)
    { 
      log.info("No " + "shopId" + " defined, creating new " + "Shop");
      shop = new Shop();
    }
    else
    { 
      shop = em.find(Shop.class, shopId);
    }
    initProduct8List();
    initCart8List();
    initProduct9List();
    initProduct12List();
    initPerson135List();
    initProject128List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("shop") private Long shopId;

  private Shop shop;

  public void setShop(Shop shop)
  { 
    log.info("setShop");
    this.shop = shop;
  }

  public Shop getShop()
  { 
    log.info("getShop");
    return shop;
  }

  public void removeProduct0(Product product7)
  { 
    this.getShop().getProducts().remove(product7);
  }

  public void addProduct0(Product product7)
  { 
    this.getShop().getProducts().add(product7);
  }

  public void removeCart0(Cart cart7)
  { 
    this.getShop().getCarts().remove(cart7);
  }

  public void addCart0(Cart cart7)
  { 
    this.getShop().getCarts().add(cart7);
  }

  public void setProduct0(Product product11)
  { 
    shop.setFirst(product11);
  }

  public void setProduct1(Product product13)
  { 
    shop.setLast(product13);
  }

  @End public String cancel()
  { 
    return "/" + "viewShop" + ".seam?" + ("shop" + "=" + shop.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getShop());
    return "/" + "viewShop" + ".seam?" + ("shop" + "=" + shop.getId() + "");
  }

  private String newProduct8;

  public void setNewProduct8(String p)
  { 
    newProduct8 = p;
  }

  public String getNewProduct8()
  { 
    return newProduct8;
  }

  public void selectProduct8(ValueChangeEvent event)
  { 
    log.info("selectProduct8" + ": new value = " + " " + event.getNewValue());
    Product product8 = (Product)event.getNewValue();
  }

  @DataModel("product8List") private Map<String, String> product8List;

  public Map<String, String> getProduct8List()
  { 
    return product8List;
  }

  @Factory("product8List") public void initProduct8List()
  { 
    log.info("initProduct8List");
    product8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product8List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCart8;

  public void setNewCart8(String p)
  { 
    newCart8 = p;
  }

  public String getNewCart8()
  { 
    return newCart8;
  }

  public void selectCart8(ValueChangeEvent event)
  { 
    log.info("selectCart8" + ": new value = " + " " + event.getNewValue());
    Cart cart8 = (Cart)event.getNewValue();
  }

  @DataModel("cart8List") private Map<String, String> cart8List;

  public Map<String, String> getCart8List()
  { 
    return cart8List;
  }

  @Factory("cart8List") public void initCart8List()
  { 
    log.info("initCart8List");
    cart8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Cart").getResultList())
    { 
      Cart p = (Cart)o;
      cart8List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct9;

  public void setNewProduct9(String p)
  { 
    newProduct9 = p;
  }

  public String getNewProduct9()
  { 
    return newProduct9;
  }

  public void selectProduct9(ValueChangeEvent event)
  { 
    log.info("selectProduct9" + ": new value = " + " " + event.getNewValue());
    Product product9 = (Product)event.getNewValue();
  }

  @DataModel("product9List") private Map<String, String> product9List;

  public Map<String, String> getProduct9List()
  { 
    return product9List;
  }

  @Factory("product9List") public void initProduct9List()
  { 
    log.info("initProduct9List");
    product9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product9List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct12;

  public void setNewProduct12(String p)
  { 
    newProduct12 = p;
  }

  public String getNewProduct12()
  { 
    return newProduct12;
  }

  public void selectProduct12(ValueChangeEvent event)
  { 
    log.info("selectProduct12" + ": new value = " + " " + event.getNewValue());
    Product product12 = (Product)event.getNewValue();
  }

  @DataModel("product12List") private Map<String, String> product12List;

  public Map<String, String> getProduct12List()
  { 
    return product12List;
  }

  @Factory("product12List") public void initProduct12List()
  { 
    log.info("initProduct12List");
    product12List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product12List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person135List") private List<Person> person135List;

  public List<Person> getPerson135List()
  { 
    log.info("getPerson135List");
    return person135List;
  }

  @Factory("person135List") public void initPerson135List()
  { 
    log.info("initPerson135List");
    person135List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project128List") private List<ResearchProject> project128List;

  public List<ResearchProject> getProject128List()
  { 
    log.info("getProject128List");
    return project128List;
  }

  @Factory("project128List") public void initProject128List()
  { 
    log.info("initProject128List");
    project128List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}