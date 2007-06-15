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
    initPerson86List();
    initProject76List();
    initJournal3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeJournal(Journal journal4)
  { 
    em.remove(journal4);
  }

  @DataModel("person86List") private List<Person> person86List;

  public List<Person> getPerson86List()
  { 
    log.info("getPerson86List");
    return person86List;
  }

  @Factory("person86List") public void initPerson86List()
  { 
    log.info("initPerson86List");
    person86List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project76List") private List<ResearchProject> project76List;

  public List<ResearchProject> getProject76List()
  { 
    log.info("getProject76List");
    return project76List;
  }

  @Factory("project76List") public void initProject76List()
  { 
    log.info("initProject76List");
    project76List = em.createQuery("from " + "ResearchProject").getResultList();
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