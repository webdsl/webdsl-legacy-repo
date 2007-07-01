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
    Conference var55 = new Conference();
    conference = var55;
    initPerson206List();
    initPerson83List();
    initProject70List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson9(Person person205)
  { 
    this.getConference().getEditors().remove(person205);
  }

  public void addPerson9(Person person205)
  { 
    this.getConference().getEditors().add(person205);
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

  private String newPerson206;

  public void setNewPerson206(String p)
  { 
    newPerson206 = p;
  }

  public String getNewPerson206()
  { 
    return newPerson206;
  }

  public void selectPerson206(ValueChangeEvent event)
  { 
    log.info("selectPerson206" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person206 = em.find(Person.class, id);
      addPerson9(person206);
    }
  }

  @DataModel("person206List") private Map<String, String> person206List;

  public Map<String, String> getPerson206List()
  { 
    return person206List;
  }

  @Factory("person206List") public void initPerson206List()
  { 
    log.info("initPerson206List");
    person206List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person206List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person83List") private List<Person> person83List;

  public List<Person> getPerson83List()
  { 
    log.info("getPerson83List");
    return person83List;
  }

  @Factory("person83List") public void initPerson83List()
  { 
    log.info("initPerson83List");
    person83List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project70List") private List<ResearchProject> project70List;

  public List<ResearchProject> getProject70List()
  { 
    log.info("getProject70List");
    return project70List;
  }

  @Factory("project70List") public void initProject70List()
  { 
    log.info("initProject70List");
    project70List = em.createQuery("from " + "ResearchProject").getResultList();
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