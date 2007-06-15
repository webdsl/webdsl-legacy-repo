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
    initPerson110List();
    initProject102List();
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

  @End public String createNewIssue()
  { 
    Issue var69 = new Issue();
    Issue issue13 = var69;
    issue13.getIssues().add(issue13);
    em.persist(issue13);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue13.getId() + "");
  }

  @End public String createNewPerson()
  { 
    Person var70 = new Person();
    Person person111 = var70;
    this.getBug().getAssigned().add(person111);
    em.persist(this.getBug());
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person111.getId() + "");
  }

  @DataModel("person110List") private List<Person> person110List;

  public List<Person> getPerson110List()
  { 
    log.info("getPerson110List");
    return person110List;
  }

  @Factory("person110List") public void initPerson110List()
  { 
    log.info("initPerson110List");
    person110List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project102List") private List<ResearchProject> project102List;

  public List<ResearchProject> getProject102List()
  { 
    log.info("getProject102List");
    return project102List;
  }

  @Factory("project102List") public void initProject102List()
  { 
    log.info("initProject102List");
    project102List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}