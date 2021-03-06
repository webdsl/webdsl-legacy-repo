package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
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
    if(technicalReportId == null)
    { 
      log.debug("No " + "technicalReportId" + " defined, creating new " + "TechnicalReport");
      technicalReport = new TechnicalReport();
    }
    else
    { 
      technicalReport = em.find(TechnicalReport.class, technicalReportId);
    }
    initPreprintof0List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("technicalReport") private Long technicalReportId;

  private TechnicalReport technicalReport;

  public void setTechnicalReport(TechnicalReport technicalReport)
  { 
    this.technicalReport = technicalReport;
  }

  public TechnicalReport getTechnicalReport()
  { 
    return technicalReport;
  }

  public void setPublication0(Publication preprintof1)
  { 
    technicalReport.setPreprintof(preprintof1);
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

  private String newPreprintof0;

  public void setNewPreprintof0(String p)
  { 
    newPreprintof0 = p;
  }

  public String getNewPreprintof0()
  { 
    return newPreprintof0;
  }

  public String selectPreprintof0()
  { 
    log.info("selectPreprintof0" + " " + newPreprintof0);
    Publication preprintof0 = em.find(Publication.class, new Long(newPreprintof0));
    setPublication0(preprintof0);
    return null;
  }

  @DataModel("preprintof0List") private Map<String, String> preprintof0List;

  public Map<String, String> getPreprintof0List()
  { 
    return preprintof0List;
  }

  @Factory("preprintof0List") public void initPreprintof0List()
  { 
    log.info("initPreprintof0List");
    preprintof0List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      preprintof0List.put(p.getName(), p.getId().toString());
    }
  }
}