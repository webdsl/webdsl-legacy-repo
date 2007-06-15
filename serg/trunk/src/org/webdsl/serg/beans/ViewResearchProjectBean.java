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
    initPerson88List();
    initProject78List();
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

  @End public String createNewPerson()
  { 
    Person var58 = new Person();
    Person person89 = var58;
    this.getResearchProject().getMembers().add(person89);
    em.persist(this.getResearchProject());
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person89.getId() + "");
  }

  @End public String createNewPublication()
  { 
    Publication var59 = new Publication();
    Publication publication15 = var59;
    this.getResearchProject().getPublications().add(publication15);
    em.persist(this.getResearchProject());
    return "/" + "editPublication" + ".seam?" + ("publication" + "=" + publication15.getId() + "");
  }

  @DataModel("person88List") private List<Person> person88List;

  public List<Person> getPerson88List()
  { 
    log.info("getPerson88List");
    return person88List;
  }

  @Factory("person88List") public void initPerson88List()
  { 
    log.info("initPerson88List");
    person88List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project78List") private List<ResearchProject> project78List;

  public List<ResearchProject> getProject78List()
  { 
    log.info("getProject78List");
    return project78List;
  }

  @Factory("project78List") public void initProject78List()
  { 
    log.info("initProject78List");
    project78List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}