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

@Stateful @Name("allPublication") public class AllPublicationBean  implements AllPublicationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allPublication" + ".initalize()");
    initPerson61List();
    initProject56List();
    initPublication4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePublication(Publication publication5)
  { 
    em.remove(publication5);
  }

  @DataModel("person61List") private List<Person> person61List;

  public List<Person> getPerson61List()
  { 
    log.info("getPerson61List");
    return person61List;
  }

  @Factory("person61List") public void initPerson61List()
  { 
    log.info("initPerson61List");
    person61List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project56List") private List<ResearchProject> project56List;

  public List<ResearchProject> getProject56List()
  { 
    log.info("getProject56List");
    return project56List;
  }

  @Factory("project56List") public void initProject56List()
  { 
    log.info("initProject56List");
    project56List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("publication4List") private List<Publication> publication4List;

  public List<Publication> getPublication4List()
  { 
    log.info("getPublication4List");
    return publication4List;
  }

  @Factory("publication4List") public void initPublication4List()
  { 
    log.info("initPublication4List");
    publication4List = em.createQuery("from " + "Publication").getResultList();
  }
}