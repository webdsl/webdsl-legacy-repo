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
    Publication var28 = new Publication();
    publication = var28;
    Person var29 = new Person();
    newAuthor1 = var29;
    initPerson133List();
    initResearchProject17List();
    initPerson58List();
    initProject53List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson1(Person person132)
  { 
    this.getPublication().getAuthors().remove(person132);
  }

  public void addPerson1(Person person132)
  { 
    this.getPublication().getAuthors().add(person132);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor1());
    Person var27 = new Person();
    newAuthor1 = var27;
  }

  public void removeResearchProject5(ResearchProject researchProject16)
  { 
    this.getPublication().getProjects().remove(researchProject16);
  }

  public void addResearchProject5(ResearchProject researchProject16)
  { 
    this.getPublication().getProjects().add(researchProject16);
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

  private String newPerson133;

  public void setNewPerson133(String p)
  { 
    newPerson133 = p;
  }

  public String getNewPerson133()
  { 
    return newPerson133;
  }

  public void selectPerson133(ValueChangeEvent event)
  { 
    log.info("selectPerson133" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person133 = em.find(Person.class, id);
      addPerson1(person133);
    }
  }

  @DataModel("person133List") private Map<String, String> person133List;

  public Map<String, String> getPerson133List()
  { 
    return person133List;
  }

  @Factory("person133List") public void initPerson133List()
  { 
    log.info("initPerson133List");
    person133List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person133List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject17;

  public void setNewResearchProject17(String p)
  { 
    newResearchProject17 = p;
  }

  public String getNewResearchProject17()
  { 
    return newResearchProject17;
  }

  public void selectResearchProject17(ValueChangeEvent event)
  { 
    log.info("selectResearchProject17" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject17 = em.find(ResearchProject.class, id);
      addResearchProject5(researchProject17);
    }
  }

  @DataModel("researchProject17List") private Map<String, String> researchProject17List;

  public Map<String, String> getResearchProject17List()
  { 
    return researchProject17List;
  }

  @Factory("researchProject17List") public void initResearchProject17List()
  { 
    log.info("initResearchProject17List");
    researchProject17List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject17List.put(p.getName(), p.getId().toString());
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