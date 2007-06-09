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

@Stateful @Name("createTechnicalReport") public class CreateTechnicalReportBean  implements CreateTechnicalReportBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createTechnicalReport" + ".initalize()");
    TechnicalReport var30 = new TechnicalReport();
    technicalReport = var30;
    initPublication6List();
    initPerson1037List();
    initProject1137List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPublication1(Publication publication7)
  { 
    technicalReport.setPreprintof(publication7);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getTechnicalReport());
    return "/" + "viewTechnicalReport" + ".seam?" + ("technicalReport" + "=" + technicalReport.getId() + "");
  }

  private String newPublication6;

  public void setNewPublication6(String p)
  { 
    newPublication6 = p;
  }

  public String getNewPublication6()
  { 
    return newPublication6;
  }

  public void selectPublication6(ValueChangeEvent event)
  { 
    log.info("selectPublication6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication6 = em.find(Publication.class, id);
      setPublication1(publication6);
    }
  }

  @DataModel("publication6List") private Map<String, String> publication6List;

  public Map<String, String> getPublication6List()
  { 
    return publication6List;
  }

  @Factory("publication6List") public void initPublication6List()
  { 
    log.info("initPublication6List");
    publication6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1037List") private List<Person> person1037List;

  public List<Person> getPerson1037List()
  { 
    log.info("getPerson1037List");
    return person1037List;
  }

  @Factory("person1037List") public void initPerson1037List()
  { 
    log.info("initPerson1037List");
    person1037List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1137List") private List<ResearchProject> project1137List;

  public List<ResearchProject> getProject1137List()
  { 
    log.info("getProject1137List");
    return project1137List;
  }

  @Factory("project1137List") public void initProject1137List()
  { 
    log.info("initProject1137List");
    project1137List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private TechnicalReport technicalReport;

  public TechnicalReport getTechnicalReport()
  { 
    log.info("getTechnicalReport");
    return technicalReport;
  }

  public void setTechnicalReport(TechnicalReport technicalReport)
  { 
    log.info("setTechnicalReport");
    this.technicalReport = technicalReport;
  }
}