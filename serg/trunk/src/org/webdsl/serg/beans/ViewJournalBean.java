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

@Stateful @Name("viewJournal") public class ViewJournalBean  implements ViewJournalBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewJournal" + ".initalize()");
    if(journalId == null)
    { 
      log.info("No " + "journalId" + " defined, creating new " + "Journal");
      journal = new Journal();
    }
    else
    { 
      journal = em.find(Journal.class, journalId);
    }
    initPerson80List();
    initProject75List();
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

  @DataModel("project75List") private List<ResearchProject> project75List;

  public List<ResearchProject> getProject75List()
  { 
    log.info("getProject75List");
    return project75List;
  }

  @Factory("project75List") public void initProject75List()
  { 
    log.info("initProject75List");
    project75List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}