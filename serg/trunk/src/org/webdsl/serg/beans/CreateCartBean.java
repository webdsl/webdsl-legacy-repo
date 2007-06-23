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
    initPerson243List();
    initProduct31List();
    initPerson142List();
    initProject135List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson18(Person person244)
  { 
    cart.setShopper(person244);
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

  private String newPerson243;

  public void setNewPerson243(String p)
  { 
    newPerson243 = p;
  }

  public String getNewPerson243()
  { 
    return newPerson243;
  }

  public void selectPerson243(ValueChangeEvent event)
  { 
    log.info("selectPerson243" + ": new value = " + " " + event.getNewValue());
    Person person243 = (Person)event.getNewValue();
  }

  @DataModel("person243List") private Map<String, String> person243List;

  public Map<String, String> getPerson243List()
  { 
    return person243List;
  }

  @Factory("person243List") public void initPerson243List()
  { 
    log.info("initPerson243List");
    person243List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person243List.put(p.getName(), p.getId().toString());
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
    Product product31 = (Product)event.getNewValue();
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

  @DataModel("person142List") private List<Person> person142List;

  public List<Person> getPerson142List()
  { 
    log.info("getPerson142List");
    return person142List;
  }

  @Factory("person142List") public void initPerson142List()
  { 
    log.info("initPerson142List");
    person142List = em.createQuery("from " + "Person").getResultList();
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