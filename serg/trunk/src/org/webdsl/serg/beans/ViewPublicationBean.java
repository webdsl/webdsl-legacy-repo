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
    initPerson65List();
    initProject55List();
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

  @End public String createNewPerson(Publication publication00, java.util.List<Person> authors0)
  { 
    Person var34 = new Person();
    Person person00 = var34;
    authors0.add(person00);
    em.persist(publication00);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person00.getId() + "");
  }

  @End public String createNewResearchProject(Publication publication10, java.util.Set<ResearchProject> projects5)
  { 
    ResearchProject var35 = new ResearchProject();
    ResearchProject researchProject10 = var35;
    projects5.add(researchProject10);
    em.persist(publication10);
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject10.getId() + "");
  }

  @DataModel("person65List") private List<Person> person65List;

  public List<Person> getPerson65List()
  { 
    log.info("getPerson65List");
    return person65List;
  }

  @Factory("person65List") public void initPerson65List()
  { 
    log.info("initPerson65List");
    person65List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project55List") private List<ResearchProject> project55List;

  public List<ResearchProject> getProject55List()
  { 
    log.info("getProject55List");
    return project55List;
  }

  @Factory("project55List") public void initProject55List()
  { 
    log.info("initProject55List");
    project55List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}