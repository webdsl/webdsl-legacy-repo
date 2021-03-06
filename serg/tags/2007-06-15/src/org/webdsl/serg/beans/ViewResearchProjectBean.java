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
    initPerson89List();
    initProject79List();
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

  @End public String createNewPerson(ResearchProject researchProject20, java.util.Set<Person> members0)
  { 
    Person var59 = new Person();
    Person person210 = var59;
    members0.add(person210);
    em.persist(researchProject20);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person210.getId() + "");
  }

  @End public String createNewPublication(ResearchProject researchProject30, java.util.Set<Publication> publications3)
  { 
    Publication var60 = new Publication();
    Publication publication21 = var60;
    publications3.add(publication21);
    em.persist(researchProject30);
    return "/" + "editPublication" + ".seam?" + ("publication" + "=" + publication21.getId() + "");
  }

  @DataModel("person89List") private List<Person> person89List;

  public List<Person> getPerson89List()
  { 
    log.info("getPerson89List");
    return person89List;
  }

  @Factory("person89List") public void initPerson89List()
  { 
    log.info("initPerson89List");
    person89List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project79List") private List<ResearchProject> project79List;

  public List<ResearchProject> getProject79List()
  { 
    log.info("getProject79List");
    return project79List;
  }

  @Factory("project79List") public void initProject79List()
  { 
    log.info("initProject79List");
    project79List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}