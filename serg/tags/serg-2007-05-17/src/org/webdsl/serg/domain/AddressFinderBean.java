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

@Stateful @Name("addressFinder") public class AddressFinderBean  implements AddressFinder
{ 
  @Logger private Log log;

  @In FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @Create public void initialize()
  { 
    findEntries();
  }

  @DataModel private List<Address> addressEntries;

  @DataModelSelection @Out(required = false) private Address selectedAddress;

  @Factory("addressEntries") public void findEntries()
  { 
    addressEntries = em.createQuery("from " + "Address").getResultList();
    log.info("call to findEntries: list = " + addressEntries);
  }

  public void refresh()
  { 
    findEntries();
  }

  public void delete()
  { 
    if(addressEntries == null)
      facesMessages.add("entries list is null");
    else
      if(selectedAddress == null)
        facesMessages.add("selection is null");
      else
      { 
        facesMessages.add("deleting " + "Address" + " #{entry.id}");
        addressEntries.remove(selectedAddress);
        em.remove(selectedAddress);
        selectedAddress = null;
      }
  }

  @Destroy @Remove public void destroy()
  { }
}