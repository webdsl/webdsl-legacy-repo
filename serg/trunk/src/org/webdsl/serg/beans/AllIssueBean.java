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
    initPerson106List();
    initProject95List();
    initIssue6List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue(Issue issue7)
  { 
    em.remove(issue7);
  }

  @DataModel("person106List") private List<Person> person106List;

  public List<Person> getPerson106List()
  { 
    log.info("getPerson106List");
    return person106List;
  }

  @Factory("person106List") public void initPerson106List()
  { 
    log.info("initPerson106List");
    person106List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project95List") private List<ResearchProject> project95List;

  public List<ResearchProject> getProject95List()
  { 
    log.info("getProject95List");
    return project95List;
  }

  @Factory("project95List") public void initProject95List()
  { 
    log.info("initProject95List");
    project95List = em.createQuery("from " + "ResearchProject").getResultList();
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