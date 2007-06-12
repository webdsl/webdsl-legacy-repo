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
    initPerson144List();
    initResearchProject25List();
    initPerson57List();
    initProject52List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson2(Person person143)
  { 
    this.getPublication().getAuthors().remove(person143);
  }

  public void addPerson2(Person person143)
  { 
    this.getPublication().getAuthors().add(person143);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor1());
    Person var27 = new Person();
    newAuthor1 = var27;
  }

  public void removeResearchProject8(ResearchProject researchProject24)
  { 
    this.getPublication().getProjects().remove(researchProject24);
  }

  public void addResearchProject8(ResearchProject researchProject24)
  { 
    this.getPublication().getProjects().add(researchProject24);
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

  private String newPerson144;

  public void setNewPerson144(String p)
  { 
    newPerson144 = p;
  }

  public String getNewPerson144()
  { 
    return newPerson144;
  }

  public void selectPerson144(ValueChangeEvent event)
  { 
    log.info("selectPerson144" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person144 = em.find(Person.class, id);
      addPerson2(person144);
    }
  }

  @DataModel("person144List") private Map<String, String> person144List;

  public Map<String, String> getPerson144List()
  { 
    return person144List;
  }

  @Factory("person144List") public void initPerson144List()
  { 
    log.info("initPerson144List");
    person144List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person144List.put(p.getName(), p.getId().toString());
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
      addResearchProject8(researchProject25);
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