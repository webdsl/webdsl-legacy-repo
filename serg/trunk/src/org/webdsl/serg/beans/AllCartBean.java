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

@Stateful @Name("allCart") public class AllCartBean  implements AllCartBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allCart" + ".initalize()");
    initPerson139List();
    initProject133List();
    initCart5List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeCart(Cart cart6)
  { 
    em.remove(cart6);
  }

  @DataModel("person139List") private List<Person> person139List;

  public List<Person> getPerson139List()
  { 
    log.info("getPerson139List");
    return person139List;
  }

  @Factory("person139List") public void initPerson139List()
  { 
    log.info("initPerson139List");
    person139List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project133List") private List<ResearchProject> project133List;

  public List<ResearchProject> getProject133List()
  { 
    log.info("getProject133List");
    return project133List;
  }

  @Factory("project133List") public void initProject133List()
  { 
    log.info("initProject133List");
    project133List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("cart5List") private List<Cart> cart5List;

  public List<Cart> getCart5List()
  { 
    log.info("getCart5List");
    return cart5List;
  }

  @Factory("cart5List") public void initCart5List()
  { 
    log.info("initCart5List");
    cart5List = em.createQuery("from " + "Cart").getResultList();
  }
}