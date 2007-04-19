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
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful @Name("addressEditor") public class AddressEditorBean  implements AddressEditor
{ 
  @Logger private Log log;

  @In private FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  private Address address;

  public Address getAddress()
  { 
    return address;
  }

  public void setAddress(Address address)
  { 
    this.address = address;
  }

  @Create public void initialize()
  { 
    address = new Address();
  }

  private boolean isNew = true;

  public boolean isNew()
  { 
    return isNew;
  }

  @Begin(join = true) public String create()
  { 
    em.persist(address);
    isNew = false;
    log.info("creating new x_class");
    return null;
  }

  public String update()
  { 
    em.joinTransaction();
    em.flush();
    log.info("updating x_class");
    return null;
  }

  @End public String delete()
  { 
    em.remove(address);
    address = null;
    log.info("deleting x_Class and going to PersonList");
    return "/find" + "Address" + ".xhtml";
  }

  @End public String done()
  { 
    if(!isNew)
      em.refresh(address);
    return "/find" + "Address" + ".xhtml";
  }

  @End public String view()
  { 
    if(!isNew)
      em.refresh(address);
    return "/view" + "Address" + ".xhtml";
  }

  @Remove @Destroy public void destroy()
  { }
}