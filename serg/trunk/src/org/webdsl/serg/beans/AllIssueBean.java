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
    initPerson96List();
    initProject91List();
    initIssue3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue(Issue issue4)
  { 
    em.remove(issue4);
  }

  @DataModel("person96List") private List<Person> person96List;

  public List<Person> getPerson96List()
  { 
    log.info("getPerson96List");
    return person96List;
  }

  @Factory("person96List") public void initPerson96List()
  { 
    log.info("initPerson96List");
    person96List = em.createQuery("from " + "Person").getResultList();
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

  @DataModel("issue3List") private List<Issue> issue3List;

  public List<Issue> getIssue3List()
  { 
    log.info("getIssue3List");
    return issue3List;
  }

  @Factory("issue3List") public void initIssue3List()
  { 
    log.info("initIssue3List");
    issue3List = em.createQuery("from " + "Issue").getResultList();
  }
}