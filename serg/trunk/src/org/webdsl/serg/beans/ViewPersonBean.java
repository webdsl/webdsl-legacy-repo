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

@Stateful @Name("viewPerson") public class ViewPersonBean  implements ViewPersonBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewPerson" + ".initalize()");
    if(personId == null)
    { 
      log.info("No " + "personId" + " defined, creating new " + "Person");
      person = new Person();
    }
    else
    { 
      person = em.find(Person.class, personId);
    }
    initPublications0();
    initOrderedPublications0();
    initProjects0();
    initPr0List();
    initPerson100List();
    initProject110List();
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

  @DataModel("pr0List") private List<ResearchProject> pr0List;

  public List<ResearchProject> getPr0List()
  { 
    log.info("getPr0List");
    return pr0List;
  }

  @Factory("pr0List") public void initPr0List()
  { 
    log.info("initPr0List");
    pr0List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("person100List") private List<Person> person100List;

  public List<Person> getPerson100List()
  { 
    log.info("getPerson100List");
    return person100List;
  }

  @Factory("person100List") public void initPerson100List()
  { 
    log.info("initPerson100List");
    person100List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project110List") private List<ResearchProject> project110List;

  public List<ResearchProject> getProject110List()
  { 
    log.info("getProject110List");
    return project110List;
  }

  @Factory("project110List") public void initProject110List()
  { 
    log.info("initProject110List");
    project110List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("publications0") private java.util.List<Publication> publications0;

  public java.util.List<Publication> getPublications0()
  { 
    log.info("getPublications0");
    return publications0;
  }

  public void setPublications0(java.util.List<Publication> publications0)
  { 
    log.info("setPublications0");
    this.publications0 = publications0;
  }

  @Factory("publications0") public void initPublications0()
  { 
    log.info("initPublications0");
    publications0 = em.createQuery("select pub from Publication as pub , Person as pers where ( pers . id = :param0 ) and ( pers member of pub . _authors )").setParameter("param0", this.getPerson().getId()).getResultList();
  }

  @DataModel("orderedPublications0") private java.util.List<Publication> orderedPublications0;

  public java.util.List<Publication> getOrderedPublications0()
  { 
    log.info("getOrderedPublications0");
    return orderedPublications0;
  }

  public void setOrderedPublications0(java.util.List<Publication> orderedPublications0)
  { 
    log.info("setOrderedPublications0");
    this.orderedPublications0 = orderedPublications0;
  }

  @Factory("orderedPublications0") public void initOrderedPublications0()
  { 
    log.info("initOrderedPublications0");
    orderedPublications0 = em.createQuery("select pub from Publication as pub , Person as pers where ( pers . id = :param1 ) and ( pers member of pub . _authors ) order by pub . _year desc").setParameter("param1", this.getPerson().getId()).getResultList();
  }

  @DataModel("projects0") private java.util.List<ResearchProject> projects0;

  public java.util.List<ResearchProject> getProjects0()
  { 
    log.info("getProjects0");
    return projects0;
  }

  public void setProjects0(java.util.List<ResearchProject> projects0)
  { 
    log.info("setProjects0");
    this.projects0 = projects0;
  }

  @Factory("projects0") public void initProjects0()
  { 
    log.info("initProjects0");
    projects0 = em.createQuery("select pr from ResearchProject as pr , Person as pers where ( pers . id = :param2 ) and ( pers member of pr . _members )").setParameter("param2", this.getPerson().getId()).getResultList();
  }
}