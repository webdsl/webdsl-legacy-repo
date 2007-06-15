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

@Stateful @Name("allConference") public class AllConferenceBean  implements AllConferenceBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allConference" + ".initalize()");
    initPerson73List();
    initProject68List();
    initConference3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeConference(Conference conference4)
  { 
    em.remove(conference4);
  }

  @DataModel("person73List") private List<Person> person73List;

  public List<Person> getPerson73List()
  { 
    log.info("getPerson73List");
    return person73List;
  }

  @Factory("person73List") public void initPerson73List()
  { 
    log.info("initPerson73List");
    person73List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project68List") private List<ResearchProject> project68List;

  public List<ResearchProject> getProject68List()
  { 
    log.info("getProject68List");
    return project68List;
  }

  @Factory("project68List") public void initProject68List()
  { 
    log.info("initProject68List");
    project68List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("conference3List") private List<Conference> conference3List;

  public List<Conference> getConference3List()
  { 
    log.info("getConference3List");
    return conference3List;
  }

  @Factory("conference3List") public void initConference3List()
  { 
    log.info("initConference3List");
    conference3List = em.createQuery("from " + "Conference").getResultList();
  }
}