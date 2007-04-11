package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static org.jboss.seam.ScopeType.CONVERSATION;
import static org.jboss.seam.ScopeType.SESSION;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

@Name("graduatedList") public class GraduatedList  implements IGraduatedList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<Graduated> graduatedList;

  @DataModelSelection @Out(required = false) private Graduated graduated;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("graduatedList") public void findEntries()
  { 
    graduatedList = em.createQuery("from " + "Graduated").getResultList();
    log.info("call to findEntries: list = " + graduatedList);
  }

  public void delete()
  { 
    if(graduatedList == null)
      facesMessages.add("entries list is null");
    else
      if(graduated == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        graduatedList.remove(graduated);
        em.remove(graduated);
        graduated = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}