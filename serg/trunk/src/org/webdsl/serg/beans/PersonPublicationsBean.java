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
    initPerson105List();
    initProject115List();
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

  @DataModel("person105List") private List<Person> person105List;

  public List<Person> getPerson105List()
  { 
    log.info("getPerson105List");
    return person105List;
  }

  @Factory("person105List") public void initPerson105List()
  { 
    log.info("initPerson105List");
    person105List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project115List") private List<ResearchProject> project115List;

  public List<ResearchProject> getProject115List()
  { 
    log.info("getProject115List");
    return project115List;
  }

  @Factory("project115List") public void initProject115List()
  { 
    log.info("initProject115List");
    project115List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}