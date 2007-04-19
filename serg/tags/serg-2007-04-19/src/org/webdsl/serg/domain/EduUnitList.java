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

@Stateful @Scope(SESSION) @Name("eduUnitListBean") public class EduUnitList  implements IEduUnitList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<EduUnit> eduUnitList;

  @DataModelSelection @Out(required = false) private EduUnit eduUnit;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("eduUnitList") public void findEntries()
  { 
    eduUnitList = em.createQuery("from " + "EduUnit").getResultList();
    log.info("call to findEntries: list = " + eduUnitList);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(eduUnitList == null)
      facesMessages.add("entries list is null");
    else
      if(eduUnit == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        eduUnitList.remove(eduUnit);
        em.remove(eduUnit);
        eduUnit = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}