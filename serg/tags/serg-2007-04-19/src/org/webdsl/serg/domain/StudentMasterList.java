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

@Stateful @Scope(SESSION) @Name("studentMasterListBean") public class StudentMasterList  implements IStudentMasterList
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @DataModel private List<StudentMaster> studentMasterList;

  @DataModelSelection @Out(required = false) private StudentMaster studentMaster;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Factory("studentMasterList") public void findEntries()
  { 
    studentMasterList = em.createQuery("from " + "StudentMaster").getResultList();
    log.info("call to findEntries: list = " + studentMasterList);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(studentMasterList == null)
      facesMessages.add("entries list is null");
    else
      if(studentMaster == null)
        facesMessages.add("entry is null");
      else
      { 
        facesMessages.add("deleting ~x_class #{entry.id}");
        studentMasterList.remove(studentMaster);
        em.remove(studentMaster);
        studentMaster = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}