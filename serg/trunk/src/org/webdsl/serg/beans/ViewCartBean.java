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

@Stateful @Name("viewCart") public class ViewCartBean  implements ViewCartBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewCart" + ".initalize()");
    if(cartId == null)
    { 
      log.info("No " + "cartId" + " defined, creating new " + "Cart");
      cart = new Cart();
    }
    else
    { 
      cart = em.find(Cart.class, cartId);
    }
    initPerson143List();
    initProject136List();
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

  @End public String createNewProduct(Cart cart10, java.util.List<Product> products0)
  { 
    Product var90 = new Product();
    Product product10 = var90;
    products0.add(product10);
    em.persist(cart10);
    return "/" + "editProduct" + ".seam?" + ("product" + "=" + product10.getId() + "");
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

  @DataModel("project136List") private List<ResearchProject> project136List;

  public List<ResearchProject> getProject136List()
  { 
    log.info("getProject136List");
    return project136List;
  }

  @Factory("project136List") public void initProject136List()
  { 
    log.info("initProject136List");
    project136List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}