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
    initPerson144List();
    initProject137List();
    initCart5List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeCart(Cart cart6)
  { 
    em.remove(cart6);
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

  @DataModel("project137List") private List<ResearchProject> project137List;

  public List<ResearchProject> getProject137List()
  { 
    log.info("getProject137List");
    return project137List;
  }

  @Factory("project137List") public void initProject137List()
  { 
    log.info("initProject137List");
    project137List = em.createQuery("from " + "ResearchProject").getResultList();
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