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
    initPerson95List();
    initProject84List();
    initResearchProject9List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeResearchProject(ResearchProject researchProject14)
  { 
    em.remove(researchProject14);
  }

  @DataModel("person95List") private List<Person> person95List;

  public List<Person> getPerson95List()
  { 
    log.info("getPerson95List");
    return person95List;
  }

  @Factory("person95List") public void initPerson95List()
  { 
    log.info("initPerson95List");
    person95List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project84List") private List<ResearchProject> project84List;

  public List<ResearchProject> getProject84List()
  { 
    log.info("getProject84List");
    return project84List;
  }

  @Factory("project84List") public void initProject84List()
  { 
    log.info("initProject84List");
    project84List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("researchProject9List") private List<ResearchProject> researchProject9List;

  public List<ResearchProject> getResearchProject9List()
  { 
    log.info("getResearchProject9List");
    return researchProject9List;
  }

  @Factory("researchProject9List") public void initResearchProject9List()
  { 
    log.info("initResearchProject9List");
    researchProject9List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}