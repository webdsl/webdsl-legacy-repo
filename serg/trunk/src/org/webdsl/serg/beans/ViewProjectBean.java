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

@Stateful @Name("viewProject") public class ViewProjectBean  implements ViewProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewProject" + ".initalize()");
    if(projectId == null)
    { 
      log.info("No " + "projectId" + " defined, creating new " + "Project");
      project = new Project();
    }
    else
    { 
      project = em.find(Project.class, projectId);
    }
    initPerson112List();
    initProject100List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("project") private Long projectId;

  private Project project;

  public void setProject(Project project)
  { 
    log.info("setProject");
    this.project = project;
  }

  public Project getProject()
  { 
    log.info("getProject");
    return project;
  }

  @End public String createNewIssue(Issue issue12, java.util.Set<Issue> issues1)
  { 
    Issue var74 = new Issue();
    Issue issue01 = var74;
    issues1.add(issue01);
    em.persist(issue12);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue01.getId() + "");
  }

  @End public String createNewPerson(Issue issue21, java.util.Set<Person> assigned1)
  { 
    Person var75 = new Person();
    Person person511 = var75;
    assigned1.add(person511);
    em.persist(issue21);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person511.getId() + "");
  }

  @DataModel("person112List") private List<Person> person112List;

  public List<Person> getPerson112List()
  { 
    log.info("getPerson112List");
    return person112List;
  }

  @Factory("person112List") public void initPerson112List()
  { 
    log.info("initPerson112List");
    person112List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project100List") private List<ResearchProject> project100List;

  public List<ResearchProject> getProject100List()
  { 
    log.info("getProject100List");
    return project100List;
  }

  @Factory("project100List") public void initProject100List()
  { 
    log.info("initProject100List");
    project100List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}