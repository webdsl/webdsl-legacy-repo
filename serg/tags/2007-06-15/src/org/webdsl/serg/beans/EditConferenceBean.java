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
    initPerson169List();
    initPerson75List();
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

  public void removePerson6(Person person168)
  { 
    this.getConference().getEditors().remove(person168);
  }

  public void addPerson6(Person person168)
  { 
    this.getConference().getEditors().add(person168);
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

  private String newPerson169;

  public void setNewPerson169(String p)
  { 
    newPerson169 = p;
  }

  public String getNewPerson169()
  { 
    return newPerson169;
  }

  public void selectPerson169(ValueChangeEvent event)
  { 
    log.info("selectPerson169" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person169 = em.find(Person.class, id);
      addPerson6(person169);
    }
  }

  @DataModel("person169List") private Map<String, String> person169List;

  public Map<String, String> getPerson169List()
  { 
    return person169List;
  }

  @Factory("person169List") public void initPerson169List()
  { 
    log.info("initPerson169List");
    person169List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person169List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person75List") private List<Person> person75List;

  public List<Person> getPerson75List()
  { 
    log.info("getPerson75List");
    return person75List;
  }

  @Factory("person75List") public void initPerson75List()
  { 
    log.info("initPerson75List");
    person75List = em.createQuery("from " + "Person").getResultList();
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