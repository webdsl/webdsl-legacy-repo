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

@Stateful @Name("allBug") public class AllBugBean  implements AllBugBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allBug" + ".initalize()");
    initPerson117List();
    initProject108List();
    initBug3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeBug(Bug bug4)
  { 
    em.remove(bug4);
  }

  @DataModel("person117List") private List<Person> person117List;

  public List<Person> getPerson117List()
  { 
    log.info("getPerson117List");
    return person117List;
  }

  @Factory("person117List") public void initPerson117List()
  { 
    log.info("initPerson117List");
    person117List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project108List") private List<ResearchProject> project108List;

  public List<ResearchProject> getProject108List()
  { 
    log.info("getProject108List");
    return project108List;
  }

  @Factory("project108List") public void initProject108List()
  { 
    log.info("initProject108List");
    project108List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("bug3List") private List<Bug> bug3List;

  public List<Bug> getBug3List()
  { 
    log.info("getBug3List");
    return bug3List;
  }

  @Factory("bug3List") public void initBug3List()
  { 
    log.info("initBug3List");
    bug3List = em.createQuery("from " + "Bug").getResultList();
  }
}