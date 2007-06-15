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

@Stateful @Name("allResearchProject") public class AllResearchProjectBean  implements AllResearchProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allResearchProject" + ".initalize()");
    initPerson90List();
    initProject80List();
    initResearchProject8List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeResearchProject(ResearchProject researchProject9)
  { 
    em.remove(researchProject9);
  }

  @DataModel("person90List") private List<Person> person90List;

  public List<Person> getPerson90List()
  { 
    log.info("getPerson90List");
    return person90List;
  }

  @Factory("person90List") public void initPerson90List()
  { 
    log.info("initPerson90List");
    person90List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project80List") private List<ResearchProject> project80List;

  public List<ResearchProject> getProject80List()
  { 
    log.info("getProject80List");
    return project80List;
  }

  @Factory("project80List") public void initProject80List()
  { 
    log.info("initProject80List");
    project80List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("researchProject8List") private List<ResearchProject> researchProject8List;

  public List<ResearchProject> getResearchProject8List()
  { 
    log.info("getResearchProject8List");
    return researchProject8List;
  }

  @Factory("researchProject8List") public void initResearchProject8List()
  { 
    log.info("initResearchProject8List");
    researchProject8List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}