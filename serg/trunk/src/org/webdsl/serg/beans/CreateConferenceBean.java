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

@Stateful @Name("createConference") public class CreateConferenceBean  implements CreateConferenceBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createConference" + ".initalize()");
    Conference var42 = new Conference();
    conference = var42;
    initPerson166List();
    initPerson71List();
    initProject66List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson7(Person person165)
  { 
    this.getConference().getEditors().remove(person165);
  }

  public void addPerson7(Person person165)
  { 
    this.getConference().getEditors().add(person165);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getConference());
    return "/" + "viewConference" + ".seam?" + ("conference" + "=" + conference.getId() + "");
  }

  private String newPerson166;

  public void setNewPerson166(String p)
  { 
    newPerson166 = p;
  }

  public String getNewPerson166()
  { 
    return newPerson166;
  }

  public void selectPerson166(ValueChangeEvent event)
  { 
    log.info("selectPerson166" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person166 = em.find(Person.class, id);
      addPerson7(person166);
    }
  }

  @DataModel("person166List") private Map<String, String> person166List;

  public Map<String, String> getPerson166List()
  { 
    return person166List;
  }

  @Factory("person166List") public void initPerson166List()
  { 
    log.info("initPerson166List");
    person166List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person166List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person71List") private List<Person> person71List;

  public List<Person> getPerson71List()
  { 
    log.info("getPerson71List");
    return person71List;
  }

  @Factory("person71List") public void initPerson71List()
  { 
    log.info("initPerson71List");
    person71List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project66List") private List<ResearchProject> project66List;

  public List<ResearchProject> getProject66List()
  { 
    log.info("getProject66List");
    return project66List;
  }

  @Factory("project66List") public void initProject66List()
  { 
    log.info("initProject66List");
    project66List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Conference conference;

  public Conference getConference()
  { 
    log.info("getConference");
    return conference;
  }

  public void setConference(Conference conference)
  { 
    log.info("setConference");
    this.conference = conference;
  }
}