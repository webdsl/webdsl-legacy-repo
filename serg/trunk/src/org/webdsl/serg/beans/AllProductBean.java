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
    initPerson135List();
    initProject129List();
    initProduct4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeProduct(Product product5)
  { 
    em.remove(product5);
  }

  @DataModel("person135List") private List<Person> person135List;

  public List<Person> getPerson135List()
  { 
    log.info("getPerson135List");
    return person135List;
  }

  @Factory("person135List") public void initPerson135List()
  { 
    log.info("initPerson135List");
    person135List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project129List") private List<ResearchProject> project129List;

  public List<ResearchProject> getProject129List()
  { 
    log.info("getProject129List");
    return project129List;
  }

  @Factory("project129List") public void initProject129List()
  { 
    log.info("initProject129List");
    project129List = em.createQuery("from " + "ResearchProject").getResultList();
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