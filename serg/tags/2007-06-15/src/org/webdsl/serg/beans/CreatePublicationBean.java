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

@Stateful @Name("createPublication") public class CreatePublicationBean  implements CreatePublicationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createPublication" + ".initalize()");
    Publication var30 = new Publication();
    publication = var30;
    Person var31 = new Person();
    newAuthor1 = var31;
    initPerson156List();
    initResearchProject27List();
    initPerson64List();
    initProject54List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson1(Person person155)
  { 
    this.getPublication().getAuthors().remove(person155);
  }

  public void addPerson1(Person person155)
  { 
    this.getPublication().getAuthors().add(person155);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor1());
    Person var29 = new Person();
    newAuthor1 = var29;
  }

  public void removeResearchProject5(ResearchProject researchProject26)
  { 
    this.getPublication().getProjects().remove(researchProject26);
  }

  public void addResearchProject5(ResearchProject researchProject26)
  { 
    this.getPublication().getProjects().add(researchProject26);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getPublication());
    return "/" + "viewPublication" + ".seam?" + ("publication" + "=" + publication.getId() + "");
  }

  private String newPerson156;

  public void setNewPerson156(String p)
  { 
    newPerson156 = p;
  }

  public String getNewPerson156()
  { 
    return newPerson156;
  }

  public void selectPerson156(ValueChangeEvent event)
  { 
    log.info("selectPerson156" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person156 = em.find(Person.class, id);
      addPerson1(person156);
    }
  }

  @DataModel("person156List") private Map<String, String> person156List;

  public Map<String, String> getPerson156List()
  { 
    return person156List;
  }

  @Factory("person156List") public void initPerson156List()
  { 
    log.info("initPerson156List");
    person156List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person156List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject27;

  public void setNewResearchProject27(String p)
  { 
    newResearchProject27 = p;
  }

  public String getNewResearchProject27()
  { 
    return newResearchProject27;
  }

  public void selectResearchProject27(ValueChangeEvent event)
  { 
    log.info("selectResearchProject27" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject27 = em.find(ResearchProject.class, id);
      addResearchProject5(researchProject27);
    }
  }

  @DataModel("researchProject27List") private Map<String, String> researchProject27List;

  public Map<String, String> getResearchProject27List()
  { 
    return researchProject27List;
  }

  @Factory("researchProject27List") public void initResearchProject27List()
  { 
    log.info("initResearchProject27List");
    researchProject27List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject27List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person64List") private List<Person> person64List;

  public List<Person> getPerson64List()
  { 
    log.info("getPerson64List");
    return person64List;
  }

  @Factory("person64List") public void initPerson64List()
  { 
    log.info("initPerson64List");
    person64List = em.createQuery("from " + "Person").getResultList();
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

  private Publication publication;

  public Publication getPublication()
  { 
    log.info("getPublication");
    return publication;
  }

  public void setPublication(Publication publication)
  { 
    log.info("setPublication");
    this.publication = publication;
  }

  private Person newAuthor1;

  public Person getNewAuthor1()
  { 
    log.info("getNewAuthor1");
    return newAuthor1;
  }

  public void setNewAuthor1(Person newAuthor1)
  { 
    log.info("setNewAuthor1");
    this.newAuthor1 = newAuthor1;
  }
}