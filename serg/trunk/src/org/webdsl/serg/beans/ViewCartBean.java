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
    initPerson138List();
    initProject132List();
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
    Product var87 = new Product();
    Product product10 = var87;
    products0.add(product10);
    em.persist(cart10);
    return "/" + "editProduct" + ".seam?" + ("product" + "=" + product10.getId() + "");
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
}