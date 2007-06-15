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
    initPerson78List();
    initProject68List();
    initConference4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeConference(Conference conference5)
  { 
    em.remove(conference5);
  }

  @DataModel("person78List") private List<Person> person78List;

  public List<Person> getPerson78List()
  { 
    log.info("getPerson78List");
    return person78List;
  }

  @Factory("person78List") public void initPerson78List()
  { 
    log.info("initPerson78List");
    person78List = em.createQuery("from " + "Person").getResultList();
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