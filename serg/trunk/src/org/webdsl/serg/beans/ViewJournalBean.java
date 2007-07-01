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
    initPerson92List();
    initProject79List();
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

  @DataModel("person92List") private List<Person> person92List;

  public List<Person> getPerson92List()
  { 
    log.info("getPerson92List");
    return person92List;
  }

  @Factory("person92List") public void initPerson92List()
  { 
    log.info("initPerson92List");
    person92List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project79List") private List<ResearchProject> project79List;

  public List<ResearchProject> getProject79List()
  { 
    log.info("getProject79List");
    return project79List;
  }

  @Factory("project79List") public void initProject79List()
  { 
    log.info("initProject79List");
    project79List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}