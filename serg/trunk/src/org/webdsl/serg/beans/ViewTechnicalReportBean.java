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

@Stateful @Name("viewTechnicalReport") public class ViewTechnicalReportBean  implements ViewTechnicalReportBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewTechnicalReport" + ".initalize()");
    if(technicalReportId == null)
    { 
      log.info("No " + "technicalReportId" + " defined, creating new " + "TechnicalReport");
      technicalReport = new TechnicalReport();
    }
    else
    { 
      technicalReport = em.find(TechnicalReport.class, technicalReportId);
    }
    initPerson76List();
    initProject63List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("technicalReport") private Long technicalReportId;

  private TechnicalReport technicalReport;

  public void setTechnicalReport(TechnicalReport technicalReport)
  { 
    log.info("setTechnicalReport");
    this.technicalReport = technicalReport;
  }

  public TechnicalReport getTechnicalReport()
  { 
    log.info("getTechnicalReport");
    return technicalReport;
  }

  @End public String createNewPerson(Publication publication01, java.util.List<Person> authors2)
  { 
    Person var46 = new Person();
    Person person01 = var46;
    authors2.add(person01);
    em.persist(publication01);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person01.getId() + "");
  }

  @End public String createNewResearchProject(Publication publication13, java.util.Set<ResearchProject> projects7)
  { 
    ResearchProject var47 = new ResearchProject();
    ResearchProject researchProject11 = var47;
    projects7.add(researchProject11);
    em.persist(publication13);
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject11.getId() + "");
  }

  @DataModel("person76List") private List<Person> person76List;

  public List<Person> getPerson76List()
  { 
    log.info("getPerson76List");
    return person76List;
  }

  @Factory("person76List") public void initPerson76List()
  { 
    log.info("initPerson76List");
    person76List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project63List") private List<ResearchProject> project63List;

  public List<ResearchProject> getProject63List()
  { 
    log.info("getProject63List");
    return project63List;
  }

  @Factory("project63List") public void initProject63List()
  { 
    log.info("initProject63List");
    project63List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}