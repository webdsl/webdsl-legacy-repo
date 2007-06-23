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
    initPerson83List();
    initProject72List();
    initConference4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeConference(Conference conference5)
  { 
    em.remove(conference5);
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

  @DataModel("project72List") private List<ResearchProject> project72List;

  public List<ResearchProject> getProject72List()
  { 
    log.info("getProject72List");
    return project72List;
  }

  @Factory("project72List") public void initProject72List()
  { 
    log.info("initProject72List");
    project72List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("conference4List") private List<Conference> conference4List;

  public List<Conference> getConference4List()
  { 
    log.info("getConference4List");
    return conference4List;
  }

  @Factory("conference4List") public void initConference4List()
  { 
    log.info("initConference4List");
    conference4List = em.createQuery("from " + "Conference").getResultList();
  }
}