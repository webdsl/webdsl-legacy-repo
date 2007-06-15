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

@Stateful @Name("allAddress") public class AllAddressBean  implements AllAddressBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allAddress" + ".initalize()");
    initPerson34List();
    initProject28List();
    initAddress3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeAddress(Address address4)
  { 
    em.remove(address4);
  }

  @DataModel("person34List") private List<Person> person34List;

  public List<Person> getPerson34List()
  { 
    log.info("getPerson34List");
    return person34List;
  }

  @Factory("person34List") public void initPerson34List()
  { 
    log.info("initPerson34List");
    person34List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project28List") private List<ResearchProject> project28List;

  public List<ResearchProject> getProject28List()
  { 
    log.info("getProject28List");
    return project28List;
  }

  @Factory("project28List") public void initProject28List()
  { 
    log.info("initProject28List");
    project28List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("address3List") private List<Address> address3List;

  public List<Address> getAddress3List()
  { 
    log.info("getAddress3List");
    return address3List;
  }

  @Factory("address3List") public void initAddress3List()
  { 
    log.info("initAddress3List");
    address3List = em.createQuery("from " + "Address").getResultList();
  }
}