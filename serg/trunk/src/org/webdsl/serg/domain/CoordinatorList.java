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

@Stateful @Scope(SESSION) @Name("coordinatorListBean") public class CoordinatorList  implements ICoordinatorList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<Coordinator> coordinatorList;

  @DataModelSelection @Out(required = false) private Coordinator coordinator;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("coordinatorList") public void findEntries()
  { 
    coordinatorList = em.createQuery("from " + "Coordinator").getResultList();
    log.info("call to findEntries: list = " + coordinatorList);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(coordinatorList == null)
      facesMessages.add("entries list is null");
    else
      if(coordinator == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        coordinatorList.remove(coordinator);
        em.remove(coordinator);
        coordinator = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}