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

@Stateful @Scope(SESSION) @Name("chairListBean") public class ChairList  implements IChairList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<Chair> chairList;

  @DataModelSelection @Out(required = false) private Chair chair;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("chairList") public void findEntries()
  { 
    chairList = em.createQuery("from " + "Chair").getResultList();
    log.info("call to findEntries: list = " + chairList);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(chairList == null)
      facesMessages.add("entries list is null");
    else
      if(chair == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        chairList.remove(chair);
        em.remove(chair);
        chair = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}