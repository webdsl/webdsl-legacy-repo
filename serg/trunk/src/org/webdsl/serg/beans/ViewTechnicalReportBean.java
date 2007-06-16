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
    initPerson69List();
    initProject59List();
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

  @End public String createNewPerson(Publication publication01, java.util.List<Person> authors1)
  { 
    Person var41 = new Person();
    Person person01 = var41;
    authors1.add(person01);
    em.persist(publication01);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person01.getId() + "");
  }

  @End public String createNewResearchProject(Publication publication13, java.util.Set<ResearchProject> projects6)
  { 
    ResearchProject var42 = new ResearchProject();
    ResearchProject researchProject11 = var42;
    projects6.add(researchProject11);
    em.persist(publication13);
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject11.getId() + "");
  }

  @DataModel("person69List") private List<Person> person69List;

  public List<Person> getPerson69List()
  { 
    log.info("getPerson69List");
    return person69List;
  }

  @Factory("person69List") public void initPerson69List()
  { 
    log.info("initPerson69List");
    person69List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project59List") private List<ResearchProject> project59List;

  public List<ResearchProject> getProject59List()
  { 
    log.info("getProject59List");
    return project59List;
  }

  @Factory("project59List") public void initProject59List()
  { 
    log.info("initProject59List");
    project59List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}