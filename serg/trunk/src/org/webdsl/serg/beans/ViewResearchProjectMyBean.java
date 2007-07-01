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

@Stateful @Name("viewResearchProjectMy") public class ViewResearchProjectMyBean  implements ViewResearchProjectMyBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewResearchProjectMy" + ".initalize()");
    if(projectId == null)
    { 
      log.info("No " + "projectId" + " defined, creating new " + "ResearchProject");
      project = new ResearchProject();
    }
    else
    { 
      project = em.find(ResearchProject.class, projectId);
    }
    initPerson22List();
    initProject9List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("project") private Long projectId;

  private ResearchProject project;

  public void setProject(ResearchProject project)
  { 
    log.info("setProject");
    this.project = project;
  }

  public ResearchProject getProject()
  { 
    log.info("getProject");
    return project;
  }

  @DataModel("person22List") private List<Person> person22List;

  public List<Person> getPerson22List()
  { 
    log.info("getPerson22List");
    return person22List;
  }

  @Factory("person22List") public void initPerson22List()
  { 
    log.info("initPerson22List");
    person22List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project9List") private List<ResearchProject> project9List;

  public List<ResearchProject> getProject9List()
  { 
    log.info("getProject9List");
    return project9List;
  }

  @Factory("project9List") public void initProject9List()
  { 
    log.info("initProject9List");
    project9List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}