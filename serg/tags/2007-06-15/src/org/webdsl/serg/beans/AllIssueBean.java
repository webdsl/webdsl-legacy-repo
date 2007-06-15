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

@Stateful @Name("allIssue") public class AllIssueBean  implements AllIssueBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allIssue" + ".initalize()");
    initPerson101List();
    initProject91List();
    initIssue6List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue(Issue issue7)
  { 
    em.remove(issue7);
  }

  @DataModel("person101List") private List<Person> person101List;

  public List<Person> getPerson101List()
  { 
    log.info("getPerson101List");
    return person101List;
  }

  @Factory("person101List") public void initPerson101List()
  { 
    log.info("initPerson101List");
    person101List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project91List") private List<ResearchProject> project91List;

  public List<ResearchProject> getProject91List()
  { 
    log.info("getProject91List");
    return project91List;
  }

  @Factory("project91List") public void initProject91List()
  { 
    log.info("initProject91List");
    project91List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("issue6List") private List<Issue> issue6List;

  public List<Issue> getIssue6List()
  { 
    log.info("getIssue6List");
    return issue6List;
  }

  @Factory("issue6List") public void initIssue6List()
  { 
    log.info("initIssue6List");
    issue6List = em.createQuery("from " + "Issue").getResultList();
  }
}