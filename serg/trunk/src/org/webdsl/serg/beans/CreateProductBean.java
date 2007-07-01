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

@Stateful @Name("createProduct") public class CreateProductBean  implements CreateProductBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createProduct" + ".initalize()");
    Product var88 = new Product();
    product = var88;
    initShop17List();
    initProduct24List();
    initProduct26List();
    initPerson141List();
    initProject132List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setShop1(Shop shop18)
  { 
    product.setShop(shop18);
  }

  public void setProduct6(Product product25)
  { 
    product.setPrevious(product25);
  }

  public void setProduct7(Product product27)
  { 
    product.setNext(product27);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getProduct());
    return "/" + "viewProduct" + ".seam?" + ("product" + "=" + product.getId() + "");
  }

  private String newShop17;

  public void setNewShop17(String p)
  { 
    newShop17 = p;
  }

  public String getNewShop17()
  { 
    return newShop17;
  }

  public void selectShop17(ValueChangeEvent event)
  { 
    log.info("selectShop17" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Shop shop17 = em.find(Shop.class, id);
      setShop1(shop17);
    }
  }

  @DataModel("shop17List") private Map<String, String> shop17List;

  public Map<String, String> getShop17List()
  { 
    return shop17List;
  }

  @Factory("shop17List") public void initShop17List()
  { 
    log.info("initShop17List");
    shop17List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Shop").getResultList())
    { 
      Shop p = (Shop)o;
      shop17List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct24;

  public void setNewProduct24(String p)
  { 
    newProduct24 = p;
  }

  public String getNewProduct24()
  { 
    return newProduct24;
  }

  public void selectProduct24(ValueChangeEvent event)
  { 
    log.info("selectProduct24" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Product product24 = em.find(Product.class, id);
      setProduct6(product24);
    }
  }

  @DataModel("product24List") private Map<String, String> product24List;

  public Map<String, String> getProduct24List()
  { 
    return product24List;
  }

  @Factory("product24List") public void initProduct24List()
  { 
    log.info("initProduct24List");
    product24List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product24List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct26;

  public void setNewProduct26(String p)
  { 
    newProduct26 = p;
  }

  public String getNewProduct26()
  { 
    return newProduct26;
  }

  public void selectProduct26(ValueChangeEvent event)
  { 
    log.info("selectProduct26" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Product product26 = em.find(Product.class, id);
      setProduct7(product26);
    }
  }

  @DataModel("product26List") private Map<String, String> product26List;

  public Map<String, String> getProduct26List()
  { 
    return product26List;
  }

  @Factory("product26List") public void initProduct26List()
  { 
    log.info("initProduct26List");
    product26List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product26List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person141List") private List<Person> person141List;

  public List<Person> getPerson141List()
  { 
    log.info("getPerson141List");
    return person141List;
  }

  @Factory("person141List") public void initPerson141List()
  { 
    log.info("initPerson141List");
    person141List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project132List") private List<ResearchProject> project132List;

  public List<ResearchProject> getProject132List()
  { 
    log.info("getProject132List");
    return project132List;
  }

  @Factory("project132List") public void initProject132List()
  { 
    log.info("initProject132List");
    project132List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Product product;

  public Product getProduct()
  { 
    log.info("getProduct");
    return product;
  }

  public void setProduct(Product product)
  { 
    log.info("setProduct");
    this.product = product;
  }
}