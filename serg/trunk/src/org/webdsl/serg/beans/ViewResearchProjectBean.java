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

@Stateful @Name("viewResearchProject") public class ViewResearchProjectBean  implements ViewResearchProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewResearchProject" + ".initalize()");
    if(researchProjectId == null)
    { 
      log.info("No " + "researchProjectId" + " defined, creating new " + "ResearchProject");
      researchProject = new ResearchProject();
    }
    else
    { 
      researchProject = em.find(ResearchProject.class, researchProjectId);
    }
    initPerson94List();
    initProject83List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("researchProject") private Long researchProjectId;

  private ResearchProject researchProject;

  public void setResearchProject(ResearchProject researchProject)
  { 
    log.info("setResearchProject");
    this.researchProject = researchProject;
  }

  public ResearchProject getResearchProject()
  { 
    log.info("getResearchProject");
    return researchProject;
  }

  @End public String createNewPerson(ResearchProject researchProject30, java.util.Set<Person> members0)
  { 
    Person var66 = new Person();
    Person person310 = var66;
    members0.add(person310);
    em.persist(researchProject30);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person310.getId() + "");
  }

  @End public String createNewPublication(ResearchProject researchProject40, java.util.Set<Publication> publications3)
  { 
    Publication var67 = new Publication();
    Publication publication21 = var67;
    publications3.add(publication21);
    em.persist(researchProject40);
    return "/" + "editPublication" + ".seam?" + ("publication" + "=" + publication21.getId() + "");
  }

  @DataModel("person94List") private List<Person> person94List;

  public List<Person> getPerson94List()
  { 
    log.info("getPerson94List");
    return person94List;
  }

  @Factory("person94List") public void initPerson94List()
  { 
    log.info("initPerson94List");
    person94List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project83List") private List<ResearchProject> project83List;

  public List<ResearchProject> getProject83List()
  { 
    log.info("getProject83List");
    return project83List;
  }

  @Factory("project83List") public void initProject83List()
  { 
    log.info("initProject83List");
    project83List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}