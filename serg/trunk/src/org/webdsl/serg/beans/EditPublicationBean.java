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
    initPerson37List();
    initResearchProject6List();
    initPerson1033List();
    initProject1133List();
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

  public void removePerson1(Person person36)
  { 
    this.getPublication().getAuthors().remove(person36);
  }

  public void addPerson1(Person person36)
  { 
    this.getPublication().getAuthors().add(person36);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor0());
    Person var25 = new Person();
    newAuthor0 = var25;
  }

  public void removeResearchProject1(ResearchProject researchProject5)
  { 
    this.getPublication().getProjects().remove(researchProject5);
  }

  public void addResearchProject1(ResearchProject researchProject5)
  { 
    this.getPublication().getProjects().add(researchProject5);
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

  private String newPerson37;

  public void setNewPerson37(String p)
  { 
    newPerson37 = p;
  }

  public String getNewPerson37()
  { 
    return newPerson37;
  }

  public void selectPerson37(ValueChangeEvent event)
  { 
    log.info("selectPerson37" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person37 = em.find(Person.class, id);
      addPerson1(person37);
    }
  }

  @DataModel("person37List") private Map<String, String> person37List;

  public Map<String, String> getPerson37List()
  { 
    return person37List;
  }

  @Factory("person37List") public void initPerson37List()
  { 
    log.info("initPerson37List");
    person37List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person37List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject6;

  public void setNewResearchProject6(String p)
  { 
    newResearchProject6 = p;
  }

  public String getNewResearchProject6()
  { 
    return newResearchProject6;
  }

  public void selectResearchProject6(ValueChangeEvent event)
  { 
    log.info("selectResearchProject6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject6 = em.find(ResearchProject.class, id);
      addResearchProject1(researchProject6);
    }
  }

  @DataModel("researchProject6List") private Map<String, String> researchProject6List;

  public Map<String, String> getResearchProject6List()
  { 
    return researchProject6List;
  }

  @Factory("researchProject6List") public void initResearchProject6List()
  { 
    log.info("initResearchProject6List");
    researchProject6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1033List") private List<Person> person1033List;

  public List<Person> getPerson1033List()
  { 
    log.info("getPerson1033List");
    return person1033List;
  }

  @Factory("person1033List") public void initPerson1033List()
  { 
    log.info("initPerson1033List");
    person1033List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1133List") private List<ResearchProject> project1133List;

  public List<ResearchProject> getProject1133List()
  { 
    log.info("getProject1133List");
    return project1133List;
  }

  @Factory("project1133List") public void initProject1133List()
  { 
    log.info("initProject1133List");
    project1133List = em.createQuery("from " + "ResearchProject").getResultList();
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