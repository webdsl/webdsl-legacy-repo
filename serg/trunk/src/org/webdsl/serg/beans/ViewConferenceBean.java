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
    initPerson82List();
    initProject71List();
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
    Person var56 = new Person();
    Person person210 = var56;
    editors0.add(person210);
    em.persist(conference00);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person210.getId() + "");
  }

  @DataModel("person82List") private List<Person> person82List;

  public List<Person> getPerson82List()
  { 
    log.info("getPerson82List");
    return person82List;
  }

  @Factory("person82List") public void initPerson82List()
  { 
    log.info("initPerson82List");
    person82List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project71List") private List<ResearchProject> project71List;

  public List<ResearchProject> getProject71List()
  { 
    log.info("getProject71List");
    return project71List;
  }

  @Factory("project71List") public void initProject71List()
  { 
    log.info("initProject71List");
    project71List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}