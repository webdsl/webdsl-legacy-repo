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

@Stateful @Name("allProduct") public class AllProductBean  implements AllProductBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allProduct" + ".initalize()");
    initPerson140List();
    initProject133List();
    initProduct4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeProduct(Product product5)
  { 
    em.remove(product5);
  }

  @DataModel("person140List") private List<Person> person140List;

  public List<Person> getPerson140List()
  { 
    log.info("getPerson140List");
    return person140List;
  }

  @Factory("person140List") public void initPerson140List()
  { 
    log.info("initPerson140List");
    person140List = em.createQuery("from " + "Person").getResultList();
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

  @DataModel("product4List") private List<Product> product4List;

  public List<Product> getProduct4List()
  { 
    log.info("getProduct4List");
    return product4List;
  }

  @Factory("product4List") public void initProduct4List()
  { 
    log.info("initProduct4List");
    product4List = em.createQuery("from " + "Product").getResultList();
  }
}