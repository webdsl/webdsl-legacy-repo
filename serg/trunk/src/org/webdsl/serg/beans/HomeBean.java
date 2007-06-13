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

@Stateful @Name("home") public class HomeBean  implements HomeBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("home" + ".initalize()");
    initGroup9List();
    initPerson20List();
    initProject19List();
  }

  @Destroy @Remove public void destroy()
  { }

  @DataModel("group9List") private List<ResearchGroup> group9List;

  public List<ResearchGroup> getGroup9List()
  { 
    log.info("getGroup9List");
    return group9List;
  }

  @Factory("group9List") public void initGroup9List()
  { 
    log.info("initGroup9List");
    group9List = em.createQuery("from " + "ResearchGroup").getResultList();
  }

  @DataModel("person20List") private List<Person> person20List;

  public List<Person> getPerson20List()
  { 
    log.info("getPerson20List");
    return person20List;
  }

  @Factory("person20List") public void initPerson20List()
  { 
    log.info("initPerson20List");
    person20List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project19List") private List<ResearchProject> project19List;

  public List<ResearchProject> getProject19List()
  { 
    log.info("getProject19List");
    return project19List;
  }

  @Factory("project19List") public void initProject19List()
  { 
    log.info("initProject19List");
    project19List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}