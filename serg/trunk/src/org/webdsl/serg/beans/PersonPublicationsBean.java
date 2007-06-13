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
    if(personId == null)
    { 
      log.info("No " + "personId" + " defined, creating new " + "Person");
      person = new Person();
    }
    else
    { 
      person = em.find(Person.class, personId);
    }
    initProjects4();
    initOrderedPublications1();
    initPerson12List();
    initProject8List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("person") private Long personId;

  private Person person;

  public void setPerson(Person person)
  { 
    log.info("setPerson");
    this.person = person;
  }

  public Person getPerson()
  { 
    log.info("getPerson");
    return person;
  }

  @DataModel("person12List") private List<Person> person12List;

  public List<Person> getPerson12List()
  { 
    log.info("getPerson12List");
    return person12List;
  }

  @Factory("person12List") public void initPerson12List()
  { 
    log.info("initPerson12List");
    person12List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project8List") private List<ResearchProject> project8List;

  public List<ResearchProject> getProject8List()
  { 
    log.info("getProject8List");
    return project8List;
  }

  @Factory("project8List") public void initProject8List()
  { 
    log.info("initProject8List");
    project8List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("projects4") private java.util.List<ResearchProject> projects4;

  public java.util.List<ResearchProject> getProjects4()
  { 
    log.info("getProjects4");
    return projects4;
  }

  public void setProjects4(java.util.List<ResearchProject> projects4)
  { 
    log.info("setProjects4");
    this.projects4 = projects4;
  }

  @Factory("projects4") public void initProjects4()
  { 
    log.info("initProjects4");
    projects4 = em.createQuery("select pr from ResearchProject as pr , Person as pers where ( pers . id = :param5 ) and ( pers member of pr . _members )").setParameter("param5", this.getPerson().getId()).getResultList();
  }

  @DataModel("orderedPublications1") private java.util.List<Publication> orderedPublications1;

  public java.util.List<Publication> getOrderedPublications1()
  { 
    log.info("getOrderedPublications1");
    return orderedPublications1;
  }

  public void setOrderedPublications1(java.util.List<Publication> orderedPublications1)
  { 
    log.info("setOrderedPublications1");
    this.orderedPublications1 = orderedPublications1;
  }

  @Factory("orderedPublications1") public void initOrderedPublications1()
  { 
    log.info("initOrderedPublications1");
    orderedPublications1 = em.createQuery("select pub from Publication as pub , Person as pers where ( pers . id = :param6 ) and ( pers member of pub . _authors ) order by pub . _year desc").setParameter("param6", this.getPerson().getId()).getResultList();
  }
}