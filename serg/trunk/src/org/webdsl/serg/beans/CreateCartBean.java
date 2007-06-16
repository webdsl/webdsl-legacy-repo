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
    Cart var86 = new Cart();
    cart = var86;
    initPerson233List();
    initProduct31List();
    initPerson137List();
    initProject131List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson18(Person person234)
  { 
    cart.setShopper(person234);
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

  private String newPerson233;

  public void setNewPerson233(String p)
  { 
    newPerson233 = p;
  }

  public String getNewPerson233()
  { 
    return newPerson233;
  }

  public void selectPerson233(ValueChangeEvent event)
  { 
    log.info("selectPerson233" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person233 = em.find(Person.class, id);
      setPerson18(person233);
    }
  }

  @DataModel("person233List") private Map<String, String> person233List;

  public Map<String, String> getPerson233List()
  { 
    return person233List;
  }

  @Factory("person233List") public void initPerson233List()
  { 
    log.info("initPerson233List");
    person233List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person233List.put(p.getName(), p.getId().toString());
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

  @DataModel("person137List") private List<Person> person137List;

  public List<Person> getPerson137List()
  { 
    log.info("getPerson137List");
    return person137List;
  }

  @Factory("person137List") public void initPerson137List()
  { 
    log.info("initPerson137List");
    person137List = em.createQuery("from " + "Person").getResultList();
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