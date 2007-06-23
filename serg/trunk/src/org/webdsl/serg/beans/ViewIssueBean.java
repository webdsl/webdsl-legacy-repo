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
    initPerson105List();
    initProject94List();
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

  @End public String createNewIssue(Issue issue10, java.util.Set<Issue> issues0)
  { 
    Issue var71 = new Issue();
    Issue issue00 = var71;
    issues0.add(issue00);
    em.persist(issue10);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue00.getId() + "");
  }

  @End public String createNewPerson(Issue issue20, java.util.Set<Person> assigned0)
  { 
    Person var72 = new Person();
    Person person510 = var72;
    assigned0.add(person510);
    em.persist(issue20);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person510.getId() + "");
  }

  @DataModel("person105List") private List<Person> person105List;

  public List<Person> getPerson105List()
  { 
    log.info("getPerson105List");
    return person105List;
  }

  @Factory("person105List") public void initPerson105List()
  { 
    log.info("initPerson105List");
    person105List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project94List") private List<ResearchProject> project94List;

  public List<ResearchProject> getProject94List()
  { 
    log.info("getProject94List");
    return project94List;
  }

  @Factory("project94List") public void initProject94List()
  { 
    log.info("initProject94List");
    project94List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}