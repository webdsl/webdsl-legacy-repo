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

@Stateful @Name("viewAddress") public class ViewAddressBean  implements ViewAddressBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewAddress" + ".initalize()");
    if(addressId == null)
    { 
      log.info("No " + "addressId" + " defined, creating new " + "Address");
      address = new Address();
    }
    else
    { 
      address = em.find(Address.class, addressId);
    }
    initPerson1014List();
    initProject1114List();
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

  @DataModel("person1014List") private List<Person> person1014List;

  public List<Person> getPerson1014List()
  { 
    log.info("getPerson1014List");
    return person1014List;
  }

  @Factory("person1014List") public void initPerson1014List()
  { 
    log.info("initPerson1014List");
    person1014List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1114List") private List<ResearchProject> project1114List;

  public List<ResearchProject> getProject1114List()
  { 
    log.info("getProject1114List");
    return project1114List;
  }

  @Factory("project1114List") public void initProject1114List()
  { 
    log.info("initProject1114List");
    project1114List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}