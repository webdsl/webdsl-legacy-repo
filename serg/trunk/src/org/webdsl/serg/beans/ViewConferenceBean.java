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

@Stateful @Name("viewConference") public class ViewConferenceBean  implements ViewConferenceBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewConference" + ".initalize()");
    if(conferenceId == null)
    { 
      log.info("No " + "conferenceId" + " defined, creating new " + "Conference");
      conference = new Conference();
    }
    else
    { 
      conference = em.find(Conference.class, conferenceId);
    }
    initPerson77List();
    initProject67List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("conference") private Long conferenceId;

  private Conference conference;

  public void setConference(Conference conference)
  { 
    log.info("setConference");
    this.conference = conference;
  }

  public Conference getConference()
  { 
    log.info("getConference");
    return conference;
  }

  @End public String createNewPerson(Conference conference00, java.util.List<Person> editors0)
  { 
    Person var51 = new Person();
    Person person11 = var51;
    editors0.add(person11);
    em.persist(conference00);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person11.getId() + "");
  }

  @DataModel("person77List") private List<Person> person77List;

  public List<Person> getPerson77List()
  { 
    log.info("getPerson77List");
    return person77List;
  }

  @Factory("person77List") public void initPerson77List()
  { 
    log.info("initPerson77List");
    person77List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project67List") private List<ResearchProject> project67List;

  public List<ResearchProject> getProject67List()
  { 
    log.info("getProject67List");
    return project67List;
  }

  @Factory("project67List") public void initProject67List()
  { 
    log.info("initProject67List");
    project67List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}