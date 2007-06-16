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

@Stateful @Name("allShop") public class AllShopBean  implements AllShopBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allShop" + ".initalize()");
    initPerson132List();
    initProject126List();
    initShop13List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeShop(Shop shop14)
  { 
    em.remove(shop14);
  }

  @DataModel("person132List") private List<Person> person132List;

  public List<Person> getPerson132List()
  { 
    log.info("getPerson132List");
    return person132List;
  }

  @Factory("person132List") public void initPerson132List()
  { 
    log.info("initPerson132List");
    person132List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project126List") private List<ResearchProject> project126List;

  public List<ResearchProject> getProject126List()
  { 
    log.info("getProject126List");
    return project126List;
  }

  @Factory("project126List") public void initProject126List()
  { 
    log.info("initProject126List");
    project126List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("shop13List") private List<Shop> shop13List;

  public List<Shop> getShop13List()
  { 
    log.info("getShop13List");
    return shop13List;
  }

  @Factory("shop13List") public void initShop13List()
  { 
    log.info("initShop13List");
    shop13List = em.createQuery("from " + "Shop").getResultList();
  }
}