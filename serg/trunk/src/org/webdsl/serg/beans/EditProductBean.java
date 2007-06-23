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

@Stateful @Name("editProduct") public class EditProductBean  implements EditProductBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editProduct" + ".initalize()");
    if(productId == null)
    { 
      log.info("No " + "productId" + " defined, creating new " + "Product");
      product = new Product();
    }
    else
    { 
      product = em.find(Product.class, productId);
    }
    initShop15List();
    initProduct20List();
    initProduct22List();
    initPerson138List();
    initProject131List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("product") private Long productId;

  private Product product;

  public void setProduct(Product product)
  { 
    log.info("setProduct");
    this.product = product;
  }

  public Product getProduct()
  { 
    log.info("getProduct");
    return product;
  }

  public void setShop0(Shop shop16)
  { 
    product.setShop(shop16);
  }

  public void setProduct4(Product product21)
  { 
    product.setPrevious(product21);
  }

  public void setProduct5(Product product23)
  { 
    product.setNext(product23);
  }

  @End public String cancel()
  { 
    return "/" + "viewProduct" + ".seam?" + ("product" + "=" + product.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getProduct());
    return "/" + "viewProduct" + ".seam?" + ("product" + "=" + product.getId() + "");
  }

  private String newShop15;

  public void setNewShop15(String p)
  { 
    newShop15 = p;
  }

  public String getNewShop15()
  { 
    return newShop15;
  }

  public void selectShop15(ValueChangeEvent event)
  { 
    log.info("selectShop15" + ": new value = " + " " + event.getNewValue());
    Shop shop15 = (Shop)event.getNewValue();
  }

  @DataModel("shop15List") private Map<String, String> shop15List;

  public Map<String, String> getShop15List()
  { 
    return shop15List;
  }

  @Factory("shop15List") public void initShop15List()
  { 
    log.info("initShop15List");
    shop15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Shop").getResultList())
    { 
      Shop p = (Shop)o;
      shop15List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct20;

  public void setNewProduct20(String p)
  { 
    newProduct20 = p;
  }

  public String getNewProduct20()
  { 
    return newProduct20;
  }

  public void selectProduct20(ValueChangeEvent event)
  { 
    log.info("selectProduct20" + ": new value = " + " " + event.getNewValue());
    Product product20 = (Product)event.getNewValue();
  }

  @DataModel("product20List") private Map<String, String> product20List;

  public Map<String, String> getProduct20List()
  { 
    return product20List;
  }

  @Factory("product20List") public void initProduct20List()
  { 
    log.info("initProduct20List");
    product20List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product20List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct22;

  public void setNewProduct22(String p)
  { 
    newProduct22 = p;
  }

  public String getNewProduct22()
  { 
    return newProduct22;
  }

  public void selectProduct22(ValueChangeEvent event)
  { 
    log.info("selectProduct22" + ": new value = " + " " + event.getNewValue());
    Product product22 = (Product)event.getNewValue();
  }

  @DataModel("product22List") private Map<String, String> product22List;

  public Map<String, String> getProduct22List()
  { 
    return product22List;
  }

  @Factory("product22List") public void initProduct22List()
  { 
    log.info("initProduct22List");
    product22List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product22List.put(p.getName(), p.getId().toString());
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

  @DataModel("project131List") private List<ResearchProject> project131List;

  public List<ResearchProject> getProject131List()
  { 
    log.info("getProject131List");
    return project131List;
  }

  @Factory("project131List") public void initProject131List()
  { 
    log.info("initProject131List");
    project131List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}