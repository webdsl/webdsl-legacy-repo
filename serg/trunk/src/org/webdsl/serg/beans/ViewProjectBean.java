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
    initPerson98List();
    initProject95List();
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

  @DataModel("person98List") private List<Person> person98List;

  public List<Person> getPerson98List()
  { 
    log.info("getPerson98List");
    return person98List;
  }

  @Factory("person98List") public void initPerson98List()
  { 
    log.info("initPerson98List");
    person98List = em.createQuery("from " + "Person").getResultList();
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
}