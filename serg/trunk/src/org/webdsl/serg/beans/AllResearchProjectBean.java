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
    initPerson85List();
    initProject80List();
    initResearchProject3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeResearchProject(ResearchProject researchProject4)
  { 
    em.remove(researchProject4);
  }

  @DataModel("person85List") private List<Person> person85List;

  public List<Person> getPerson85List()
  { 
    log.info("getPerson85List");
    return person85List;
  }

  @Factory("person85List") public void initPerson85List()
  { 
    log.info("initPerson85List");
    person85List = em.createQuery("from " + "Person").getResultList();
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

  @DataModel("researchProject3List") private List<ResearchProject> researchProject3List;

  public List<ResearchProject> getResearchProject3List()
  { 
    log.info("getResearchProject3List");
    return researchProject3List;
  }

  @Factory("researchProject3List") public void initResearchProject3List()
  { 
    log.info("initResearchProject3List");
    researchProject3List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}