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
    Person var26 = new Person();
    newAuthor0 = var26;
    initPerson131List();
    initResearchProject15List();
    initPerson57List();
    initProject52List();
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

  public void removePerson0(Person person130)
  { 
    this.getPublication().getAuthors().remove(person130);
  }

  public void addPerson0(Person person130)
  { 
    this.getPublication().getAuthors().add(person130);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor0());
    Person var25 = new Person();
    newAuthor0 = var25;
  }

  public void removeResearchProject4(ResearchProject researchProject14)
  { 
    this.getPublication().getProjects().remove(researchProject14);
  }

  public void addResearchProject4(ResearchProject researchProject14)
  { 
    this.getPublication().getProjects().add(researchProject14);
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

  private String newPerson131;

  public void setNewPerson131(String p)
  { 
    newPerson131 = p;
  }

  public String getNewPerson131()
  { 
    return newPerson131;
  }

  public void selectPerson131(ValueChangeEvent event)
  { 
    log.info("selectPerson131" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person131 = em.find(Person.class, id);
      addPerson0(person131);
    }
  }

  @DataModel("person131List") private Map<String, String> person131List;

  public Map<String, String> getPerson131List()
  { 
    return person131List;
  }

  @Factory("person131List") public void initPerson131List()
  { 
    log.info("initPerson131List");
    person131List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person131List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject15;

  public void setNewResearchProject15(String p)
  { 
    newResearchProject15 = p;
  }

  public String getNewResearchProject15()
  { 
    return newResearchProject15;
  }

  public void selectResearchProject15(ValueChangeEvent event)
  { 
    log.info("selectResearchProject15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject15 = em.find(ResearchProject.class, id);
      addResearchProject4(researchProject15);
    }
  }

  @DataModel("researchProject15List") private Map<String, String> researchProject15List;

  public Map<String, String> getResearchProject15List()
  { 
    return researchProject15List;
  }

  @Factory("researchProject15List") public void initResearchProject15List()
  { 
    log.info("initResearchProject15List");
    researchProject15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject15List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person57List") private List<Person> person57List;

  public List<Person> getPerson57List()
  { 
    log.info("getPerson57List");
    return person57List;
  }

  @Factory("person57List") public void initPerson57List()
  { 
    log.info("initPerson57List");
    person57List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project52List") private List<ResearchProject> project52List;

  public List<ResearchProject> getProject52List()
  { 
    log.info("getProject52List");
    return project52List;
  }

  @Factory("project52List") public void initProject52List()
  { 
    log.info("initProject52List");
    project52List = em.createQuery("from " + "ResearchProject").getResultList();
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