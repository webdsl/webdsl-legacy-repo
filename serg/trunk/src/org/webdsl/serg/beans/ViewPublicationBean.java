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

@Stateful @Name("viewPublication") public class ViewPublicationBean  implements ViewPublicationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewPublication" + ".initalize()");
    if(publicationId == null)
    { 
      log.info("No " + "publicationId" + " defined, creating new " + "Publication");
      publication = new Publication();
    }
    else
    { 
      publication = em.find(Publication.class, publicationId);
    }
    initPerson59List();
    initProject54List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("publication") private Long publicationId;

  private Publication publication;

  public void setPublication(Publication publication)
  { 
    log.info("setPublication");
    this.publication = publication;
  }

  public Publication getPublication()
  { 
    log.info("getPublication");
    return publication;
  }

  @End public String createNewPerson()
  { 
    Person var31 = new Person();
    Person person60 = var31;
    this.getPublication().getAuthors().add(person60);
    em.persist(this.getPublication());
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person60.getId() + "");
  }

  @End public String createNewResearchProject()
  { 
    ResearchProject var32 = new ResearchProject();
    ResearchProject researchProject1 = var32;
    this.getPublication().getProjects().add(researchProject1);
    em.persist(this.getPublication());
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject1.getId() + "");
  }

  @DataModel("person59List") private List<Person> person59List;

  public List<Person> getPerson59List()
  { 
    log.info("getPerson59List");
    return person59List;
  }

  @Factory("person59List") public void initPerson59List()
  { 
    log.info("initPerson59List");
    person59List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project54List") private List<ResearchProject> project54List;

  public List<ResearchProject> getProject54List()
  { 
    log.info("getProject54List");
    return project54List;
  }

  @Factory("project54List") public void initProject54List()
  { 
    log.info("initProject54List");
    project54List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}