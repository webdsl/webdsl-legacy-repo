package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.faces.event.ValueChangeEvent;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.ejb.Remove;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Factory;
import org.webdsl.serg.domain.*;

@Stateful @Name("editAddress") public class EditAddressBean  implements EditAddressBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editAddress" + ".initalize()");
    if(addressId == null)
    { 
      log.info("No " + "addressId" + " defined, creating new " + "Address");
      address = new Address();
    }
    else
    { 
      address = em.find(Address.class, addressId);
    }
    initPerson25List();
    initProject24List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("address") private Long addressId;

  private Address address;

  public void setAddress(Address address)
  { 
    log.info("setAddress");
    this.address = address;
  }

  public Address getAddress()
  { 
    log.info("getAddress");
    return address;
  }

  @End public String cancel()
  { 
    return "/" + "viewAddress" + ".seam?" + ("address" + "=" + address.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getAddress());
    return "/" + "viewAddress" + ".seam?" + ("address" + "=" + address.getId() + "");
  }

  @DataModel("person25List") private List<Person> person25List;

  public List<Person> getPerson25List()
  { 
    log.info("getPerson25List");
    return person25List;
  }

  @Factory("person25List") public void initPerson25List()
  { 
    log.info("initPerson25List");
    person25List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project24List") private List<ResearchProject> project24List;

  public List<ResearchProject> getProject24List()
  { 
    log.info("getProject24List");
    return project24List;
  }

  @Factory("project24List") public void initProject24List()
  { 
    log.info("initProject24List");
    project24List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}