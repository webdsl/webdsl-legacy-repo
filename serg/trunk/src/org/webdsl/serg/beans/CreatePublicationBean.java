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
    Publication var34 = new Publication();
    publication = var34;
    Person var35 = new Person();
    newAuthor1 = var35;
    initPerson186List();
    initResearchProject28List();
    initPerson67List();
    initProject54List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson1(Person person185)
  { 
    this.getPublication().getAuthors().remove(person185);
  }

  public void addPerson1(Person person185)
  { 
    this.getPublication().getAuthors().add(person185);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor1());
    Person var33 = new Person();
    newAuthor1 = var33;
  }

  public void removeResearchProject5(ResearchProject researchProject27)
  { 
    this.getPublication().getProjects().remove(researchProject27);
  }

  public void addResearchProject5(ResearchProject researchProject27)
  { 
    this.getPublication().getProjects().add(researchProject27);
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

  private String newPerson186;

  public void setNewPerson186(String p)
  { 
    newPerson186 = p;
  }

  public String getNewPerson186()
  { 
    return newPerson186;
  }

  public void selectPerson186(ValueChangeEvent event)
  { 
    log.info("selectPerson186" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person186 = em.find(Person.class, id);
      addPerson1(person186);
    }
  }

  @DataModel("person186List") private Map<String, String> person186List;

  public Map<String, String> getPerson186List()
  { 
    return person186List;
  }

  @Factory("person186List") public void initPerson186List()
  { 
    log.info("initPerson186List");
    person186List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person186List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject28;

  public void setNewResearchProject28(String p)
  { 
    newResearchProject28 = p;
  }

  public String getNewResearchProject28()
  { 
    return newResearchProject28;
  }

  public void selectResearchProject28(ValueChangeEvent event)
  { 
    log.info("selectResearchProject28" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject28 = em.find(ResearchProject.class, id);
      addResearchProject5(researchProject28);
    }
  }

  @DataModel("researchProject28List") private Map<String, String> researchProject28List;

  public Map<String, String> getResearchProject28List()
  { 
    return researchProject28List;
  }

  @Factory("researchProject28List") public void initResearchProject28List()
  { 
    log.info("initResearchProject28List");
    researchProject28List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject28List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person67List") private List<Person> person67List;

  public List<Person> getPerson67List()
  { 
    log.info("getPerson67List");
    return person67List;
  }

  @Factory("person67List") public void initPerson67List()
  { 
    log.info("initPerson67List");
    person67List = em.createQuery("from " + "Person").getResultList();
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