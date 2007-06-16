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
    Conference var52 = new Conference();
    conference = var52;
    initPerson182List();
    initPerson76List();
    initProject66List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson7(Person person181)
  { 
    this.getConference().getEditors().remove(person181);
  }

  public void addPerson7(Person person181)
  { 
    this.getConference().getEditors().add(person181);
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

  private String newPerson182;

  public void setNewPerson182(String p)
  { 
    newPerson182 = p;
  }

  public String getNewPerson182()
  { 
    return newPerson182;
  }

  public void selectPerson182(ValueChangeEvent event)
  { 
    log.info("selectPerson182" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person182 = em.find(Person.class, id);
      addPerson7(person182);
    }
  }

  @DataModel("person182List") private Map<String, String> person182List;

  public Map<String, String> getPerson182List()
  { 
    return person182List;
  }

  @Factory("person182List") public void initPerson182List()
  { 
    log.info("initPerson182List");
    person182List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person182List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person76List") private List<Person> person76List;

  public List<Person> getPerson76List()
  { 
    log.info("getPerson76List");
    return person76List;
  }

  @Factory("person76List") public void initPerson76List()
  { 
    log.info("initPerson76List");
    person76List = em.createQuery("from " + "Person").getResultList();
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