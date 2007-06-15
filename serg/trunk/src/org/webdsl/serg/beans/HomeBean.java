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
    initPerson21List();
    initProject20List();
    initX0List();
    initX1List();
    initX2List();
    initX3List();
    initX4List();
    initX5List();
  }

  @Destroy @Remove public void destroy()
  { }

  @DataModel("person21List") private List<Person> person21List;

  public List<Person> getPerson21List()
  { 
    log.info("getPerson21List");
    return person21List;
  }

  @Factory("person21List") public void initPerson21List()
  { 
    log.info("initPerson21List");
    person21List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project20List") private List<ResearchProject> project20List;

  public List<ResearchProject> getProject20List()
  { 
    log.info("getProject20List");
    return project20List;
  }

  @Factory("project20List") public void initProject20List()
  { 
    log.info("initProject20List");
    project20List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("x0List") private List<ResearchGroup> x0List;

  public List<ResearchGroup> getX0List()
  { 
    log.info("getX0List");
    return x0List;
  }

  @Factory("x0List") public void initX0List()
  { 
    log.info("initX0List");
    x0List = em.createQuery("from " + "ResearchGroup").getResultList();
  }

  @DataModel("x1List") private List<ResearchProject> x1List;

  public List<ResearchProject> getX1List()
  { 
    log.info("getX1List");
    return x1List;
  }

  @Factory("x1List") public void initX1List()
  { 
    log.info("initX1List");
    x1List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("x2List") private List<Forum> x2List;

  public List<Forum> getX2List()
  { 
    log.info("getX2List");
    return x2List;
  }

  @Factory("x2List") public void initX2List()
  { 
    log.info("initX2List");
    x2List = em.createQuery("from " + "Forum").getResultList();
  }

  @DataModel("x3List") private List<Blog> x3List;

  public List<Blog> getX3List()
  { 
    log.info("getX3List");
    return x3List;
  }

  @Factory("x3List") public void initX3List()
  { 
    log.info("initX3List");
    x3List = em.createQuery("from " + "Blog").getResultList();
  }

  @DataModel("x4List") private List<Project> x4List;

  public List<Project> getX4List()
  { 
    log.info("getX4List");
    return x4List;
  }

  @Factory("x4List") public void initX4List()
  { 
    log.info("initX4List");
    x4List = em.createQuery("from " + "Project").getResultList();
  }

  @DataModel("x5List") private List<Person> x5List;

  public List<Person> getX5List()
  { 
    log.info("getX5List");
    return x5List;
  }

  @Factory("x5List") public void initX5List()
  { 
    log.info("initX5List");
    x5List = em.createQuery("from " + "Person").getResultList();
  }
}