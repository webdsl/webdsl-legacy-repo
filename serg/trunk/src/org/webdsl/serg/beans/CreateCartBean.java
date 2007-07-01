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

@Stateful @Name("createCart") public class CreateCartBean  implements CreateCartBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createCart" + ".initalize()");
    Cart var89 = new Cart();
    cart = var89;
    initPerson257List();
    initProduct31List();
    initPerson144List();
    initProject135List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson18(Person person258)
  { 
    cart.setShopper(person258);
  }

  public void removeProduct3(Product product30)
  { 
    this.getCart().getProducts().remove(product30);
  }

  public void addProduct3(Product product30)
  { 
    this.getCart().getProducts().add(product30);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getCart());
    return "/" + "viewCart" + ".seam?" + ("cart" + "=" + cart.getId() + "");
  }

  private String newPerson257;

  public void setNewPerson257(String p)
  { 
    newPerson257 = p;
  }

  public String getNewPerson257()
  { 
    return newPerson257;
  }

  public void selectPerson257(ValueChangeEvent event)
  { 
    log.info("selectPerson257" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person257 = em.find(Person.class, id);
      setPerson18(person257);
    }
  }

  @DataModel("person257List") private Map<String, String> person257List;

  public Map<String, String> getPerson257List()
  { 
    return person257List;
  }

  @Factory("person257List") public void initPerson257List()
  { 
    log.info("initPerson257List");
    person257List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person257List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProduct31;

  public void setNewProduct31(String p)
  { 
    newProduct31 = p;
  }

  public String getNewProduct31()
  { 
    return newProduct31;
  }

  public void selectProduct31(ValueChangeEvent event)
  { 
    log.info("selectProduct31" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Product product31 = em.find(Product.class, id);
      addProduct3(product31);
    }
  }

  @DataModel("product31List") private Map<String, String> product31List;

  public Map<String, String> getProduct31List()
  { 
    return product31List;
  }

  @Factory("product31List") public void initProduct31List()
  { 
    log.info("initProduct31List");
    product31List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Product").getResultList())
    { 
      Product p = (Product)o;
      product31List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person144List") private List<Person> person144List;

  public List<Person> getPerson144List()
  { 
    log.info("getPerson144List");
    return person144List;
  }

  @Factory("person144List") public void initPerson144List()
  { 
    log.info("initPerson144List");
    person144List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project135List") private List<ResearchProject> project135List;

  public List<ResearchProject> getProject135List()
  { 
    log.info("getProject135List");
    return project135List;
  }

  @Factory("project135List") public void initProject135List()
  { 
    log.info("initProject135List");
    project135List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Cart cart;

  public Cart getCart()
  { 
    log.info("getCart");
    return cart;
  }

  public void setCart(Cart cart)
  { 
    log.info("setCart");
    this.cart = cart;
  }
}