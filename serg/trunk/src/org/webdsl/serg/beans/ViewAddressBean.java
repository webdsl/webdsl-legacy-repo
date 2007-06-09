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
    initPerson1012List();
    initProject1112List();
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

  @DataModel("person1012List") private List<Person> person1012List;

  public List<Person> getPerson1012List()
  { 
    log.info("getPerson1012List");
    return person1012List;
  }

  @Factory("person1012List") public void initPerson1012List()
  { 
    log.info("initPerson1012List");
    person1012List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1112List") private List<ResearchProject> project1112List;

  public List<ResearchProject> getProject1112List()
  { 
    log.info("getProject1112List");
    return project1112List;
  }

  @Factory("project1112List") public void initProject1112List()
  { 
    log.info("initProject1112List");
    project1112List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}