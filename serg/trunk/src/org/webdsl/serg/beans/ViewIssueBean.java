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

@Stateful @Name("viewIssue") public class ViewIssueBean  implements ViewIssueBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewIssue" + ".initalize()");
    if(issueId == null)
    { 
      log.info("No " + "issueId" + " defined, creating new " + "Issue");
      issue = new Issue();
    }
    else
    { 
      issue = em.find(Issue.class, issueId);
    }
    initPerson100List();
    initProject89List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("issue") private Long issueId;

  private Issue issue;

  public void setIssue(Issue issue)
  { 
    log.info("setIssue");
    this.issue = issue;
  }

  public Issue getIssue()
  { 
    log.info("getIssue");
    return issue;
  }

  @End public String createNewIssue()
  { 
    Issue var63 = new Issue();
    Issue issue3 = var63;
    issue3.getIssues().add(issue3);
    em.persist(issue3);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue3.getId() + "");
  }

  @End public String createNewPerson()
  { 
    Person var64 = new Person();
    Person person101 = var64;
    this.getIssue().getAssigned().add(person101);
    em.persist(this.getIssue());
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person101.getId() + "");
  }

  @DataModel("person100List") private List<Person> person100List;

  public List<Person> getPerson100List()
  { 
    log.info("getPerson100List");
    return person100List;
  }

  @Factory("person100List") public void initPerson100List()
  { 
    log.info("initPerson100List");
    person100List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project89List") private List<ResearchProject> project89List;

  public List<ResearchProject> getProject89List()
  { 
    log.info("getProject89List");
    return project89List;
  }

  @Factory("project89List") public void initProject89List()
  { 
    log.info("initProject89List");
    project89List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}