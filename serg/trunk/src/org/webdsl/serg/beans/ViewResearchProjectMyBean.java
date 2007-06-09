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
    initPerson106List();
    initProject116List();
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

  @DataModel("project116List") private List<ResearchProject> project116List;

  public List<ResearchProject> getProject116List()
  { 
    log.info("getProject116List");
    return project116List;
  }

  @Factory("project116List") public void initProject116List()
  { 
    log.info("initProject116List");
    project116List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}