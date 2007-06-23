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
    initPerson190List();
    initPerson80List();
    initProject69List();
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

  public void removePerson8(Person person189)
  { 
    this.getConference().getEditors().remove(person189);
  }

  public void addPerson8(Person person189)
  { 
    this.getConference().getEditors().add(person189);
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

  private String newPerson190;

  public void setNewPerson190(String p)
  { 
    newPerson190 = p;
  }

  public String getNewPerson190()
  { 
    return newPerson190;
  }

  public void selectPerson190(ValueChangeEvent event)
  { 
    log.info("selectPerson190" + ": new value = " + " " + event.getNewValue());
    Person person190 = (Person)event.getNewValue();
  }

  @DataModel("person190List") private Map<String, String> person190List;

  public Map<String, String> getPerson190List()
  { 
    return person190List;
  }

  @Factory("person190List") public void initPerson190List()
  { 
    log.info("initPerson190List");
    person190List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person190List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person80List") private List<Person> person80List;

  public List<Person> getPerson80List()
  { 
    log.info("getPerson80List");
    return person80List;
  }

  @Factory("person80List") public void initPerson80List()
  { 
    log.info("initPerson80List");
    person80List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project69List") private List<ResearchProject> project69List;

  public List<ResearchProject> getProject69List()
  { 
    log.info("getProject69List");
    return project69List;
  }

  @Factory("project69List") public void initProject69List()
  { 
    log.info("initProject69List");
    project69List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}