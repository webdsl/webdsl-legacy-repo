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

@Stateful @Name("editTechnicalReport") public class EditTechnicalReportBean  implements EditTechnicalReportBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editTechnicalReport" + ".initalize()");
    if(technicalReportId == null)
    { 
      log.info("No " + "technicalReportId" + " defined, creating new " + "TechnicalReport");
      technicalReport = new TechnicalReport();
    }
    else
    { 
      technicalReport = em.find(TechnicalReport.class, technicalReportId);
    }
    initPublication4List();
    initPerson1036List();
    initProject1136List();
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

  public void setPublication0(Publication publication5)
  { 
    technicalReport.setPreprintof(publication5);
  }

  @End public String cancel()
  { 
    return "/" + "viewTechnicalReport" + ".seam?" + ("technicalReport" + "=" + technicalReport.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getTechnicalReport());
    return "/" + "viewTechnicalReport" + ".seam?" + ("technicalReport" + "=" + technicalReport.getId() + "");
  }

  private String newPublication4;

  public void setNewPublication4(String p)
  { 
    newPublication4 = p;
  }

  public String getNewPublication4()
  { 
    return newPublication4;
  }

  public void selectPublication4(ValueChangeEvent event)
  { 
    log.info("selectPublication4" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication4 = em.find(Publication.class, id);
      setPublication0(publication4);
    }
  }

  @DataModel("publication4List") private Map<String, String> publication4List;

  public Map<String, String> getPublication4List()
  { 
    return publication4List;
  }

  @Factory("publication4List") public void initPublication4List()
  { 
    log.info("initPublication4List");
    publication4List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication4List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1036List") private List<Person> person1036List;

  public List<Person> getPerson1036List()
  { 
    log.info("getPerson1036List");
    return person1036List;
  }

  @Factory("person1036List") public void initPerson1036List()
  { 
    log.info("initPerson1036List");
    person1036List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1136List") private List<ResearchProject> project1136List;

  public List<ResearchProject> getProject1136List()
  { 
    log.info("getProject1136List");
    return project1136List;
  }

  @Factory("project1136List") public void initProject1136List()
  { 
    log.info("initProject1136List");
    project1136List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}