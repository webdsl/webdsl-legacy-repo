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

@Stateful @Name("createJournal") public class CreateJournalBean  implements CreateJournalBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createJournal" + ".initalize()");
    Journal var64 = new Journal();
    journal = var64;
    initPerson91List();
    initProject78List();
  }

  @Destroy @Remove public void destroy()
  { }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getJournal());
    return "/" + "viewJournal" + ".seam?" + ("journal" + "=" + journal.getId() + "");
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

  @DataModel("project78List") private List<ResearchProject> project78List;

  public List<ResearchProject> getProject78List()
  { 
    log.info("getProject78List");
    return project78List;
  }

  @Factory("project78List") public void initProject78List()
  { 
    log.info("initProject78List");
    project78List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Journal journal;

  public Journal getJournal()
  { 
    log.info("getJournal");
    return journal;
  }

  public void setJournal(Journal journal)
  { 
    log.info("setJournal");
    this.journal = journal;
  }
}