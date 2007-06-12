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
    initGroup8List();
    initPerson19List();
    initProject18List();
  }

  @Destroy @Remove public void destroy()
  { }

  @DataModel("group8List") private List<ResearchGroup> group8List;

  public List<ResearchGroup> getGroup8List()
  { 
    log.info("getGroup8List");
    return group8List;
  }

  @Factory("group8List") public void initGroup8List()
  { 
    log.info("initGroup8List");
    group8List = em.createQuery("from " + "ResearchGroup").getResultList();
  }

  @DataModel("person19List") private List<Person> person19List;

  public List<Person> getPerson19List()
  { 
    log.info("getPerson19List");
    return person19List;
  }

  @Factory("person19List") public void initPerson19List()
  { 
    log.info("initPerson19List");
    person19List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project18List") private List<ResearchProject> project18List;

  public List<ResearchProject> getProject18List()
  { 
    log.info("getProject18List");
    return project18List;
  }

  @Factory("project18List") public void initProject18List()
  { 
    log.info("initProject18List");
    project18List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}