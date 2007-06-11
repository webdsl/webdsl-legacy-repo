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
    initPerson46List();
    initResearchProject23List();
    initPerson1035List();
    initProject1135List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson2(Person person45)
  { 
    this.getPublication().getAuthors().remove(person45);
  }

  public void addPerson2(Person person45)
  { 
    this.getPublication().getAuthors().add(person45);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor1());
    Person var27 = new Person();
    newAuthor1 = var27;
  }

  public void removeResearchProject8(ResearchProject researchProject22)
  { 
    this.getPublication().getProjects().remove(researchProject22);
  }

  public void addResearchProject8(ResearchProject researchProject22)
  { 
    this.getPublication().getProjects().add(researchProject22);
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

  private String newPerson46;

  public void setNewPerson46(String p)
  { 
    newPerson46 = p;
  }

  public String getNewPerson46()
  { 
    return newPerson46;
  }

  public void selectPerson46(ValueChangeEvent event)
  { 
    log.info("selectPerson46" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person46 = em.find(Person.class, id);
      addPerson2(person46);
    }
  }

  @DataModel("person46List") private Map<String, String> person46List;

  public Map<String, String> getPerson46List()
  { 
    return person46List;
  }

  @Factory("person46List") public void initPerson46List()
  { 
    log.info("initPerson46List");
    person46List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person46List.put(p.getName(), p.getId().toString());
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
      addResearchProject8(researchProject23);
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

  @DataModel("person1035List") private List<Person> person1035List;

  public List<Person> getPerson1035List()
  { 
    log.info("getPerson1035List");
    return person1035List;
  }

  @Factory("person1035List") public void initPerson1035List()
  { 
    log.info("initPerson1035List");
    person1035List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1135List") private List<ResearchProject> project1135List;

  public List<ResearchProject> getProject1135List()
  { 
    log.info("getProject1135List");
    return project1135List;
  }

  @Factory("project1135List") public void initProject1135List()
  { 
    log.info("initProject1135List");
    project1135List = em.createQuery("from " + "ResearchProject").getResultList();
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