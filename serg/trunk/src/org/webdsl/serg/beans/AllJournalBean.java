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

@Stateful @Name("allJournal") public class AllJournalBean  implements AllJournalBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allJournal" + ".initalize()");
    initPerson91List();
    initProject80List();
    initJournal3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeJournal(Journal journal4)
  { 
    em.remove(journal4);
  }

  @DataModel("person91List") private List<Person> person91List;

  public List<Person> getPerson91List()
  { 
    log.info("getPerson91List");
    return person91List;
  }

  @Factory("person91List") public void initPerson91List()
  { 
    log.info("initPerson91List");
    person91List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project80List") private List<ResearchProject> project80List;

  public List<ResearchProject> getProject80List()
  { 
    log.info("getProject80List");
    return project80List;
  }

  @Factory("project80List") public void initProject80List()
  { 
    log.info("initProject80List");
    project80List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("journal3List") private List<Journal> journal3List;

  public List<Journal> getJournal3List()
  { 
    log.info("getJournal3List");
    return journal3List;
  }

  @Factory("journal3List") public void initJournal3List()
  { 
    log.info("initJournal3List");
    journal3List = em.createQuery("from " + "Journal").getResultList();
  }
}