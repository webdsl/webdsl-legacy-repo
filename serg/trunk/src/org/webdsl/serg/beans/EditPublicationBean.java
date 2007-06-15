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
    Person var28 = new Person();
    newAuthor0 = var28;
    initPerson149List();
    initResearchProject15List();
    initPerson58List();
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

  public void removePerson0(Person person148)
  { 
    this.getPublication().getAuthors().remove(person148);
  }

  public void addPerson0(Person person148)
  { 
    this.getPublication().getAuthors().add(person148);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor0());
    Person var27 = new Person();
    newAuthor0 = var27;
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

  private String newPerson149;

  public void setNewPerson149(String p)
  { 
    newPerson149 = p;
  }

  public String getNewPerson149()
  { 
    return newPerson149;
  }

  public void selectPerson149(ValueChangeEvent event)
  { 
    log.info("selectPerson149" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person149 = em.find(Person.class, id);
      addPerson0(person149);
    }
  }

  @DataModel("person149List") private Map<String, String> person149List;

  public Map<String, String> getPerson149List()
  { 
    return person149List;
  }

  @Factory("person149List") public void initPerson149List()
  { 
    log.info("initPerson149List");
    person149List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person149List.put(p.getName(), p.getId().toString());
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

  @DataModel("person58List") private List<Person> person58List;

  public List<Person> getPerson58List()
  { 
    log.info("getPerson58List");
    return person58List;
  }

  @Factory("person58List") public void initPerson58List()
  { 
    log.info("initPerson58List");
    person58List = em.createQuery("from " + "Person").getResultList();
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