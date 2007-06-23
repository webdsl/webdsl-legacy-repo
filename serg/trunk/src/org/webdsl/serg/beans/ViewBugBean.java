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

@Stateful @Name("viewBug") public class ViewBugBean  implements ViewBugBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewBug" + ".initalize()");
    if(bugId == null)
    { 
      log.info("No " + "bugId" + " defined, creating new " + "Bug");
      bug = new Bug();
    }
    else
    { 
      bug = em.find(Bug.class, bugId);
    }
    initPerson114List();
    initProject107List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("bug") private Long bugId;

  private Bug bug;

  public void setBug(Bug bug)
  { 
    log.info("setBug");
    this.bug = bug;
  }

  public Bug getBug()
  { 
    log.info("getBug");
    return bug;
  }

  @End public String createNewIssue(Issue issue16, java.util.Set<Issue> issues2)
  { 
    Issue var77 = new Issue();
    Issue issue02 = var77;
    issues2.add(issue02);
    em.persist(issue16);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue02.getId() + "");
  }

  @End public String createNewPerson(Issue issue22, java.util.Set<Person> assigned2)
  { 
    Person var78 = new Person();
    Person person512 = var78;
    assigned2.add(person512);
    em.persist(issue22);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person512.getId() + "");
  }

  @DataModel("person114List") private List<Person> person114List;

  public List<Person> getPerson114List()
  { 
    log.info("getPerson114List");
    return person114List;
  }

  @Factory("person114List") public void initPerson114List()
  { 
    log.info("initPerson114List");
    person114List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project107List") private List<ResearchProject> project107List;

  public List<ResearchProject> getProject107List()
  { 
    log.info("getProject107List");
    return project107List;
  }

  @Factory("project107List") public void initProject107List()
  { 
    log.info("initProject107List");
    project107List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}