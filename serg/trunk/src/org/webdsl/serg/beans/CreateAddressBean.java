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

@Stateful @Name("createAddress") public class CreateAddressBean  implements CreateAddressBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createAddress" + ".initalize()");
    Address var23 = new Address();
    address = var23;
    initPerson33List();
    initProject26List();
  }

  @Destroy @Remove public void destroy()
  { }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getAddress());
    return "/" + "viewAddress" + ".seam?" + ("address" + "=" + address.getId() + "");
  }

  @DataModel("person33List") private List<Person> person33List;

  public List<Person> getPerson33List()
  { 
    log.info("getPerson33List");
    return person33List;
  }

  @Factory("person33List") public void initPerson33List()
  { 
    log.info("initPerson33List");
    person33List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project26List") private List<ResearchProject> project26List;

  public List<ResearchProject> getProject26List()
  { 
    log.info("getProject26List");
    return project26List;
  }

  @Factory("project26List") public void initProject26List()
  { 
    log.info("initProject26List");
    project26List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Address address;

  public Address getAddress()
  { 
    log.info("getAddress");
    return address;
  }

  public void setAddress(Address address)
  { 
    log.info("setAddress");
    this.address = address;
  }
}