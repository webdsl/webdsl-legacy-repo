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

@Stateful @Name("editJournal") public class EditJournalBean  implements EditJournalBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editJournal" + ".initalize()");
    if(journalId == null)
    { 
      log.info("No " + "journalId" + " defined, creating new " + "Journal");
      journal = new Journal();
    }
    else
    { 
      journal = em.find(Journal.class, journalId);
    }
    initPerson83List();
    initProject73List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("journal") private Long journalId;

  private Journal journal;

  public void setJournal(Journal journal)
  { 
    log.info("setJournal");
    this.journal = journal;
  }

  public Journal getJournal()
  { 
    log.info("getJournal");
    return journal;
  }

  @End public String cancel()
  { 
    return "/" + "viewJournal" + ".seam?" + ("journal" + "=" + journal.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getJournal());
    return "/" + "viewJournal" + ".seam?" + ("journal" + "=" + journal.getId() + "");
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

  @DataModel("project73List") private List<ResearchProject> project73List;

  public List<ResearchProject> getProject73List()
  { 
    log.info("getProject73List");
    return project73List;
  }

  @Factory("project73List") public void initProject73List()
  { 
    log.info("initProject73List");
    project73List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}