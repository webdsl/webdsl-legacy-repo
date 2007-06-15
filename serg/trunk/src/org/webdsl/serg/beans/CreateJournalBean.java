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
    Journal var57 = new Journal();
    journal = var57;
    initPerson84List();
    initProject74List();
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

  @DataModel("person84List") private List<Person> person84List;

  public List<Person> getPerson84List()
  { 
    log.info("getPerson84List");
    return person84List;
  }

  @Factory("person84List") public void initPerson84List()
  { 
    log.info("initPerson84List");
    person84List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project74List") private List<ResearchProject> project74List;

  public List<ResearchProject> getProject74List()
  { 
    log.info("getProject74List");
    return project74List;
  }

  @Factory("project74List") public void initProject74List()
  { 
    log.info("initProject74List");
    project74List = em.createQuery("from " + "ResearchProject").getResultList();
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