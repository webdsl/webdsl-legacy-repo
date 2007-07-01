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

@Stateful @Name("allColloquium") public class AllColloquiumBean  implements AllColloquiumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allColloquium" + ".initalize()");
    initPerson61List();
    initProject48List();
    initColloquium5List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeColloquium(Colloquium colloquium6)
  { 
    em.remove(colloquium6);
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

  @DataModel("project48List") private List<ResearchProject> project48List;

  public List<ResearchProject> getProject48List()
  { 
    log.info("getProject48List");
    return project48List;
  }

  @Factory("project48List") public void initProject48List()
  { 
    log.info("initProject48List");
    project48List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("colloquium5List") private List<Colloquium> colloquium5List;

  public List<Colloquium> getColloquium5List()
  { 
    log.info("getColloquium5List");
    return colloquium5List;
  }

  @Factory("colloquium5List") public void initColloquium5List()
  { 
    log.info("initColloquium5List");
    colloquium5List = em.createQuery("from " + "Colloquium").getResultList();
  }
}