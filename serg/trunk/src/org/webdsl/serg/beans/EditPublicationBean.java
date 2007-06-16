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

@Stateful @Name("editPublication") public class EditPublicationBean  implements EditPublicationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editPublication" + ".initalize()");
    if(publicationId == null)
    { 
      log.info("No " + "publicationId" + " defined, creating new " + "Publication");
      publication = new Publication();
    }
    else
    { 
      publication = em.find(Publication.class, publicationId);
    }
    Person var32 = new Person();
    newAuthor0 = var32;
    initPerson165List();
    initResearchProject25List();
    initPerson63List();
    initProject53List();
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

  public void removePerson0(Person person164)
  { 
    this.getPublication().getAuthors().remove(person164);
  }

  public void addPerson0(Person person164)
  { 
    this.getPublication().getAuthors().add(person164);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor0());
    Person var31 = new Person();
    newAuthor0 = var31;
  }

  public void removeResearchProject4(ResearchProject researchProject24)
  { 
    this.getPublication().getProjects().remove(researchProject24);
  }

  public void addResearchProject4(ResearchProject researchProject24)
  { 
    this.getPublication().getProjects().add(researchProject24);
  }

  @End public String cancel()
  { 
    return "/" + "viewPublication" + ".seam?" + ("publication" + "=" + publication.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getPublication());
    return "/" + "viewPublication" + ".seam?" + ("publication" + "=" + publication.getId() + "");
  }

  private String newPerson165;

  public void setNewPerson165(String p)
  { 
    newPerson165 = p;
  }

  public String getNewPerson165()
  { 
    return newPerson165;
  }

  public void selectPerson165(ValueChangeEvent event)
  { 
    log.info("selectPerson165" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person165 = em.find(Person.class, id);
      addPerson0(person165);
    }
  }

  @DataModel("person165List") private Map<String, String> person165List;

  public Map<String, String> getPerson165List()
  { 
    return person165List;
  }

  @Factory("person165List") public void initPerson165List()
  { 
    log.info("initPerson165List");
    person165List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person165List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject25;

  public void setNewResearchProject25(String p)
  { 
    newResearchProject25 = p;
  }

  public String getNewResearchProject25()
  { 
    return newResearchProject25;
  }

  public void selectResearchProject25(ValueChangeEvent event)
  { 
    log.info("selectResearchProject25" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject25 = em.find(ResearchProject.class, id);
      addResearchProject4(researchProject25);
    }
  }

  @DataModel("researchProject25List") private Map<String, String> researchProject25List;

  public Map<String, String> getResearchProject25List()
  { 
    return researchProject25List;
  }

  @Factory("researchProject25List") public void initResearchProject25List()
  { 
    log.info("initResearchProject25List");
    researchProject25List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject25List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person63List") private List<Person> person63List;

  public List<Person> getPerson63List()
  { 
    log.info("getPerson63List");
    return person63List;
  }

  @Factory("person63List") public void initPerson63List()
  { 
    log.info("initPerson63List");
    person63List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project53List") private List<ResearchProject> project53List;

  public List<ResearchProject> getProject53List()
  { 
    log.info("getProject53List");
    return project53List;
  }

  @Factory("project53List") public void initProject53List()
  { 
    log.info("initProject53List");
    project53List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Person newAuthor0;

  public Person getNewAuthor0()
  { 
    log.info("getNewAuthor0");
    return newAuthor0;
  }

  public void setNewAuthor0(Person newAuthor0)
  { 
    log.info("setNewAuthor0");
    this.newAuthor0 = newAuthor0;
  }
}