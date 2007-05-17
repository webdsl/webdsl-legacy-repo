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

@Stateful @Name("userFinder") public class UserFinderBean  implements UserFinder
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Create public void initialize()
  { 
    findEntries();
  }

  @DataModel private List<User> userEntries;

  @DataModelSelection @Out(required = false) private User selectedUser;

  @Factory("userEntries") public void findEntries()
  { 
    userEntries = em.createQuery("from " + "User").getResultList();
    log.info("call to findEntries: list = " + userEntries);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(userEntries == null)
      facesMessages.add("entries list is null");
    else
      if(selectedUser == null)
        facesMessages.add("selection is null");
      else
      { 
        facesMessages.add("deleting " + "User" + " #{entry.id}");
        userEntries.remove(selectedUser);
        em.remove(selectedUser);
        selectedUser = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}