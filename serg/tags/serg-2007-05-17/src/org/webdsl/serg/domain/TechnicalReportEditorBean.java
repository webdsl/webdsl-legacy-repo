package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful @Name("technicalReportEditor") public class TechnicalReportEditorBean  implements TechnicalReportEditor
{ 
  @Logger private Log log;

  @In private FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  private TechnicalReport technicalReport;

  public TechnicalReport getTechnicalReport()
  { 
    return technicalReport;
  }

  public void setTechnicalReport(TechnicalReport technicalReport)
  { 
    this.technicalReport = technicalReport;
  }

  @Create public void initialize()
  { 
    technicalReport = new TechnicalReport();
  }

  private boolean isNew = true;

  public boolean isNew()
  { 
    return isNew;
  }

  @Begin(join = true) public String create()
  { 
    em.persist(technicalReport);
    isNew = false;
    log.info("creating new " + "technicalReport");
    return null;
  }

  @RequestParameter("technicalReportId") private Long technicalReportId;

  @Begin(join = true) public String edit()
  { 
    technicalReport = em.find(TechnicalReport.class, technicalReportId);
    isNew = false;
    log.info("loaded new " + "technicalReport" + " for editing " + technicalReport);
    return "editAddress";
  }

  public String save()
  { 
    em.joinTransaction();
    em.flush();
    log.info("saving " + "technicalReport");
    return null;
  }

  @End public String delete()
  { 
    em.remove(technicalReport);
    technicalReport = null;
    log.info("deleting " + "TechnicalReport");
    return "find" + "TechnicalReport";
  }

  @End public String done()
  { 
    if(!isNew)
      em.refresh(technicalReport);
    return "find" + "TechnicalReport";
  }

  @End public String view()
  { 
    if(!isNew)
      em.refresh(technicalReport);
    return "view" + "TechnicalReport";
  }

  @Remove @Destroy public void destroy()
  { }
}