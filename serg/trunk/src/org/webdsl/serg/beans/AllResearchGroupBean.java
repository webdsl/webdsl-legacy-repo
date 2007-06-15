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

@Stateful @Name("allResearchGroup") public class AllResearchGroupBean  implements AllResearchGroupBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allResearchGroup" + ".initalize()");
    initPerson93List();
    initProject83List();
    initResearchGroup6List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeResearchGroup(ResearchGroup researchGroup7)
  { 
    em.remove(researchGroup7);
  }

  @DataModel("person93List") private List<Person> person93List;

  public List<Person> getPerson93List()
  { 
    log.info("getPerson93List");
    return person93List;
  }

  @Factory("person93List") public void initPerson93List()
  { 
    log.info("initPerson93List");
    person93List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project83List") private List<ResearchProject> project83List;

  public List<ResearchProject> getProject83List()
  { 
    log.info("getProject83List");
    return project83List;
  }

  @Factory("project83List") public void initProject83List()
  { 
    log.info("initProject83List");
    project83List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("researchGroup6List") private List<ResearchGroup> researchGroup6List;

  public List<ResearchGroup> getResearchGroup6List()
  { 
    log.info("getResearchGroup6List");
    return researchGroup6List;
  }

  @Factory("researchGroup6List") public void initResearchGroup6List()
  { 
    log.info("initResearchGroup6List");
    researchGroup6List = em.createQuery("from " + "ResearchGroup").getResultList();
  }
}