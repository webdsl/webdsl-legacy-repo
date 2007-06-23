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

@Stateful @Name("allTechnicalReport") public class AllTechnicalReportBean  implements AllTechnicalReportBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allTechnicalReport" + ".initalize()");
    initPerson75List();
    initProject64List();
    initTechnicalReport3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeTechnicalReport(TechnicalReport technicalReport4)
  { 
    em.remove(technicalReport4);
  }

  @DataModel("person75List") private List<Person> person75List;

  public List<Person> getPerson75List()
  { 
    log.info("getPerson75List");
    return person75List;
  }

  @Factory("person75List") public void initPerson75List()
  { 
    log.info("initPerson75List");
    person75List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project64List") private List<ResearchProject> project64List;

  public List<ResearchProject> getProject64List()
  { 
    log.info("getProject64List");
    return project64List;
  }

  @Factory("project64List") public void initProject64List()
  { 
    log.info("initProject64List");
    project64List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("technicalReport3List") private List<TechnicalReport> technicalReport3List;

  public List<TechnicalReport> getTechnicalReport3List()
  { 
    log.info("getTechnicalReport3List");
    return technicalReport3List;
  }

  @Factory("technicalReport3List") public void initTechnicalReport3List()
  { 
    log.info("initTechnicalReport3List");
    technicalReport3List = em.createQuery("from " + "TechnicalReport").getResultList();
  }
}