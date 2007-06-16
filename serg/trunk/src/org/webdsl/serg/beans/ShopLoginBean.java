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

@Stateful @Name("shopLogin") public class ShopLoginBean  implements ShopLoginBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("shopLogin" + ".initalize()");
    if(shopId == null)
    { 
      log.info("No " + "shopId" + " defined, creating new " + "Shop");
      shop = new Shop();
    }
    else
    { 
      shop = em.find(Shop.class, shopId);
    }
    User var21 = new User();
    user = var21;
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

  @End public String addProduct(Shop shop10)
  { 
    Product var20 = new Product();
    Product p6 = var20;
    p6.setShop(shop10);
    p6.setPrevious(shop10.getLast());
    p6.setNext(shop10.getFirst());
    shop10.getLast().setNext(p6);
    shop10.setLast(p6);
    shop10.getProducts().add(p6);
    em.persist(shop10);
    return "/" + "editProduct" + ".seam?" + ("product" + "=" + p6.getId() + "");
  }

  @Out(scope = ScopeType.SESSION, required=false)
  private User loggedInUser;
  
  @End public String login()
  { 
	List<User> users = 
	  em.createQuery(
	     "select u from User as u " +
	     "where (u._username = :param1) and (u._password = :param2)")
	  .setParameter("param1", user.getUsername())
	  .setParameter("param2", user.getPassword())
	  .getResultList();
	
	if (users.size() != 1) {
	  facesMessages.add("invalid username / password combination");
	} else {
	  loggedInUser = users.get(0);
	}
	  
    return "/" + "viewShop" + ".seam?" + ("shop" + "=" + shop.getId() + "");
  }

  private User user;

  public User getUser()
  { 
    log.info("getUser");
    return user;
  }

  public void setUser(User user)
  { 
    log.info("setUser");
    this.user = user;
  }
}