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
    initPerson104List();
    initProject96List();
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
    Issue var67 = new Issue();
    Issue issue01 = var67;
    issues1.add(issue01);
    em.persist(issue12);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue01.getId() + "");
  }

  @End public String createNewPerson(Issue issue21, java.util.Set<Person> assigned1)
  { 
    Person var68 = new Person();
    Person person411 = var68;
    assigned1.add(person411);
    em.persist(issue21);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person411.getId() + "");
  }

  @DataModel("person104List") private List<Person> person104List;

  public List<Person> getPerson104List()
  { 
    log.info("getPerson104List");
    return person104List;
  }

  @Factory("person104List") public void initPerson104List()
  { 
    log.info("initPerson104List");
    person104List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project96List") private List<ResearchProject> project96List;

  public List<ResearchProject> getProject96List()
  { 
    log.info("getProject96List");
    return project96List;
  }

  @Factory("project96List") public void initProject96List()
  { 
    log.info("initProject96List");
    project96List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}