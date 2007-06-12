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
    initPerson142List();
    initResearchProject23List();
    initPerson56List();
    initProject51List();
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

  public void removePerson1(Person person141)
  { 
    this.getPublication().getAuthors().remove(person141);
  }

  public void addPerson1(Person person141)
  { 
    this.getPublication().getAuthors().add(person141);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor0());
    Person var25 = new Person();
    newAuthor0 = var25;
  }

  public void removeResearchProject7(ResearchProject researchProject22)
  { 
    this.getPublication().getProjects().remove(researchProject22);
  }

  public void addResearchProject7(ResearchProject researchProject22)
  { 
    this.getPublication().getProjects().add(researchProject22);
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

  private String newPerson142;

  public void setNewPerson142(String p)
  { 
    newPerson142 = p;
  }

  public String getNewPerson142()
  { 
    return newPerson142;
  }

  public void selectPerson142(ValueChangeEvent event)
  { 
    log.info("selectPerson142" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person142 = em.find(Person.class, id);
      addPerson1(person142);
    }
  }

  @DataModel("person142List") private Map<String, String> person142List;

  public Map<String, String> getPerson142List()
  { 
    return person142List;
  }

  @Factory("person142List") public void initPerson142List()
  { 
    log.info("initPerson142List");
    person142List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person142List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject23;

  public void setNewResearchProject23(String p)
  { 
    newResearchProject23 = p;
  }

  public String getNewResearchProject23()
  { 
    return newResearchProject23;
  }

  public void selectResearchProject23(ValueChangeEvent event)
  { 
    log.info("selectResearchProject23" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject23 = em.find(ResearchProject.class, id);
      addResearchProject7(researchProject23);
    }
  }

  @DataModel("researchProject23List") private Map<String, String> researchProject23List;

  public Map<String, String> getResearchProject23List()
  { 
    return researchProject23List;
  }

  @Factory("researchProject23List") public void initResearchProject23List()
  { 
    log.info("initResearchProject23List");
    researchProject23List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject23List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person56List") private List<Person> person56List;

  public List<Person> getPerson56List()
  { 
    log.info("getPerson56List");
    return person56List;
  }

  @Factory("person56List") public void initPerson56List()
  { 
    log.info("initPerson56List");
    person56List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project51List") private List<ResearchProject> project51List;

  public List<ResearchProject> getProject51List()
  { 
    log.info("getProject51List");
    return project51List;
  }

  @Factory("project51List") public void initProject51List()
  { 
    log.info("initProject51List");
    project51List = em.createQuery("from " + "ResearchProject").getResultList();
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