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
    initPerson192List();
    initPerson81List();
    initProject70List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson9(Person person191)
  { 
    this.getConference().getEditors().remove(person191);
  }

  public void addPerson9(Person person191)
  { 
    this.getConference().getEditors().add(person191);
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

  private String newPerson192;

  public void setNewPerson192(String p)
  { 
    newPerson192 = p;
  }

  public String getNewPerson192()
  { 
    return newPerson192;
  }

  public void selectPerson192(ValueChangeEvent event)
  { 
    log.info("selectPerson192" + ": new value = " + " " + event.getNewValue());
    Person person192 = (Person)event.getNewValue();
  }

  @DataModel("person192List") private Map<String, String> person192List;

  public Map<String, String> getPerson192List()
  { 
    return person192List;
  }

  @Factory("person192List") public void initPerson192List()
  { 
    log.info("initPerson192List");
    person192List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person192List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person81List") private List<Person> person81List;

  public List<Person> getPerson81List()
  { 
    log.info("getPerson81List");
    return person81List;
  }

  @Factory("person81List") public void initPerson81List()
  { 
    log.info("initPerson81List");
    person81List = em.createQuery("from " + "Person").getResultList();
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