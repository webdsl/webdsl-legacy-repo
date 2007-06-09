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
    Address var17 = new Address();
    address = var17;
    initPerson1013List();
    initProject1113List();
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

  @DataModel("person1013List") private List<Person> person1013List;

  public List<Person> getPerson1013List()
  { 
    log.info("getPerson1013List");
    return person1013List;
  }

  @Factory("person1013List") public void initPerson1013List()
  { 
    log.info("initPerson1013List");
    person1013List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1113List") private List<ResearchProject> project1113List;

  public List<ResearchProject> getProject1113List()
  { 
    log.info("getProject1113List");
    return project1113List;
  }

  @Factory("project1113List") public void initProject1113List()
  { 
    log.info("initProject1113List");
    project1113List = em.createQuery("from " + "ResearchProject").getResultList();
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