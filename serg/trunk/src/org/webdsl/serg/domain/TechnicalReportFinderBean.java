package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.SESSION;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.webdsl.serg.domain.Address;

@Stateful @Name("technicalReportFinder") public class TechnicalReportFinderBean  implements TechnicalReportFinder
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Create public void initialize()
  { 
    findEntries();
  }

  @DataModel private List<TechnicalReport> technicalReportEntries;

  @DataModelSelection @Out(required = false) private TechnicalReport selectedTechnicalReport;

  @Factory("technicalReportEntries") public void findEntries()
  { 
    technicalReportEntries = em.createQuery("from " + "TechnicalReport").getResultList();
    log.info("call to findEntries: list = " + technicalReportEntries);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(technicalReportEntries == null)
      facesMessages.add("entries list is null");
    else
      if(selectedTechnicalReport == null)
        facesMessages.add("selection is null");
      else
      { 
        facesMessages.add("deleting " + "TechnicalReport" + " #{entry.id}");
        technicalReportEntries.remove(selectedTechnicalReport);
        em.remove(selectedTechnicalReport);
        selectedTechnicalReport = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}