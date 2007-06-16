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

@Stateful @Name("viewShop") public class ViewShopBean  implements ViewShopBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewShop" + ".initalize()");
    if(shopId == null)
    { 
      log.info("No " + "shopId" + " defined, creating new " + "Shop");
      shop = new Shop();
    }
    else
    { 
      shop = em.find(Shop.class, shopId);
    }
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

  @End public String addProduct(Shop shop4)
  { 
    Product var18 = new Product();
    Product p4 = var18;
    p4.setShop(shop4);
    p4.setPrevious(shop4.getLast());
    p4.setNext(shop4.getFirst());
    shop4.getLast().setNext(p4);
    shop4.setLast(p4);
    shop4.getProducts().add(p4);
    em.persist(shop4);
    return "/" + "editProduct" + ".seam?" + ("product" + "=" + p4.getId() + "");
  }
}