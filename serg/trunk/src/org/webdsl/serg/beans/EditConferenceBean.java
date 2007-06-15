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

@Stateful @Name("editConference") public class EditConferenceBean  implements EditConferenceBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editConference" + ".initalize()");
    if(conferenceId == null)
    { 
      log.info("No " + "conferenceId" + " defined, creating new " + "Conference");
      conference = new Conference();
    }
    else
    { 
      conference = em.find(Conference.class, conferenceId);
    }
    initPerson164List();
    initPerson70List();
    initProject65List();
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

  public void removePerson6(Person person163)
  { 
    this.getConference().getEditors().remove(person163);
  }

  public void addPerson6(Person person163)
  { 
    this.getConference().getEditors().add(person163);
  }

  @End public String cancel()
  { 
    return "/" + "viewConference" + ".seam?" + ("conference" + "=" + conference.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getConference());
    return "/" + "viewConference" + ".seam?" + ("conference" + "=" + conference.getId() + "");
  }

  private String newPerson164;

  public void setNewPerson164(String p)
  { 
    newPerson164 = p;
  }

  public String getNewPerson164()
  { 
    return newPerson164;
  }

  public void selectPerson164(ValueChangeEvent event)
  { 
    log.info("selectPerson164" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person164 = em.find(Person.class, id);
      addPerson6(person164);
    }
  }

  @DataModel("person164List") private Map<String, String> person164List;

  public Map<String, String> getPerson164List()
  { 
    return person164List;
  }

  @Factory("person164List") public void initPerson164List()
  { 
    log.info("initPerson164List");
    person164List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person164List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person70List") private List<Person> person70List;

  public List<Person> getPerson70List()
  { 
    log.info("getPerson70List");
    return person70List;
  }

  @Factory("person70List") public void initPerson70List()
  { 
    log.info("initPerson70List");
    person70List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project65List") private List<ResearchProject> project65List;

  public List<ResearchProject> getProject65List()
  { 
    log.info("getProject65List");
    return project65List;
  }

  @Factory("project65List") public void initProject65List()
  { 
    log.info("initProject65List");
    project65List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}