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

@Stateful @Name("viewProduct") public class ViewProductBean  implements ViewProductBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewProduct" + ".initalize()");
    if(productId == null)
    { 
      log.info("No " + "productId" + " defined, creating new " + "Product");
      product = new Product();
    }
    else
    { 
      product = em.find(Product.class, productId);
    }
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

  @End public String addProduct(Shop shop7)
  { 
    Product var19 = new Product();
    Product p5 = var19;
    p5.setShop(shop7);
    p5.setPrevious(shop7.getLast());
    p5.setNext(shop7.getFirst());
    shop7.getLast().setNext(p5);
    shop7.setLast(p5);
    shop7.getProducts().add(p5);
    em.persist(shop7);
    return "/" + "editProduct" + ".seam?" + ("product" + "=" + p5.getId() + "");
  }
}