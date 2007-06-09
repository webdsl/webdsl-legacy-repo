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

@Stateful @Name("personPublications") public class PersonPublicationsBean  implements PersonPublicationsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("personPublications" + ".initalize()");
    if(pId == null)
    { 
      log.info("No " + "pId" + " defined, creating new " + "Person");
      p = new Person();
    }
    else
    { 
      p = em.find(Person.class, pId);
    }
    initPr3List();
    initPerson104List();
    initProject114List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("p") private Long pId;

  private Person p;

  public void setP(Person p)
  { 
    log.info("setP");
    this.p = p;
  }

  public Person getP()
  { 
    log.info("getP");
    return p;
  }

  @DataModel("pr3List") private List<ResearchProject> pr3List;

  public List<ResearchProject> getPr3List()
  { 
    log.info("getPr3List");
    return pr3List;
  }

  @Factory("pr3List") public void initPr3List()
  { 
    log.info("initPr3List");
    pr3List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("person104List") private List<Person> person104List;

  public List<Person> getPerson104List()
  { 
    log.info("getPerson104List");
    return person104List;
  }

  @Factory("person104List") public void initPerson104List()
  { 
    log.info("initPerson104List");
    person104List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project114List") private List<ResearchProject> project114List;

  public List<ResearchProject> getProject114List()
  { 
    log.info("getProject114List");
    return project114List;
  }

  @Factory("project114List") public void initProject114List()
  { 
    log.info("initProject114List");
    project114List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}