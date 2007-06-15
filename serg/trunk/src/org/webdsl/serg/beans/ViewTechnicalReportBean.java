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
    initPerson64List();
    initProject58List();
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

  @End public String createNewPerson()
  { 
    Person var38 = new Person();
    Person person65 = var38;
    this.getTechnicalReport().getAuthors().add(person65);
    em.persist(this.getTechnicalReport());
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person65.getId() + "");
  }

  @End public String createNewResearchProject()
  { 
    ResearchProject var39 = new ResearchProject();
    ResearchProject researchProject2 = var39;
    this.getTechnicalReport().getProjects().add(researchProject2);
    em.persist(this.getTechnicalReport());
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject2.getId() + "");
  }

  @DataModel("person64List") private List<Person> person64List;

  public List<Person> getPerson64List()
  { 
    log.info("getPerson64List");
    return person64List;
  }

  @Factory("person64List") public void initPerson64List()
  { 
    log.info("initPerson64List");
    person64List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project58List") private List<ResearchProject> project58List;

  public List<ResearchProject> getProject58List()
  { 
    log.info("getProject58List");
    return project58List;
  }

  @Factory("project58List") public void initProject58List()
  { 
    log.info("initProject58List");
    project58List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}