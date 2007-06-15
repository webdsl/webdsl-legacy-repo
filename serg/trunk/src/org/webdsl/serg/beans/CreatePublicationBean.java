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
    Publication var29 = new Publication();
    publication = var29;
    Person var30 = new Person();
    newAuthor1 = var30;
    initPerson155List();
    initResearchProject22List();
    initPerson58List();
    initProject53List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson1(Person person154)
  { 
    this.getPublication().getAuthors().remove(person154);
  }

  public void addPerson1(Person person154)
  { 
    this.getPublication().getAuthors().add(person154);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor1());
    Person var28 = new Person();
    newAuthor1 = var28;
  }

  public void removeResearchProject5(ResearchProject researchProject21)
  { 
    this.getPublication().getProjects().remove(researchProject21);
  }

  public void addResearchProject5(ResearchProject researchProject21)
  { 
    this.getPublication().getProjects().add(researchProject21);
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

  private String newPerson155;

  public void setNewPerson155(String p)
  { 
    newPerson155 = p;
  }

  public String getNewPerson155()
  { 
    return newPerson155;
  }

  public void selectPerson155(ValueChangeEvent event)
  { 
    log.info("selectPerson155" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person155 = em.find(Person.class, id);
      addPerson1(person155);
    }
  }

  @DataModel("person155List") private Map<String, String> person155List;

  public Map<String, String> getPerson155List()
  { 
    return person155List;
  }

  @Factory("person155List") public void initPerson155List()
  { 
    log.info("initPerson155List");
    person155List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person155List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject22;

  public void setNewResearchProject22(String p)
  { 
    newResearchProject22 = p;
  }

  public String getNewResearchProject22()
  { 
    return newResearchProject22;
  }

  public void selectResearchProject22(ValueChangeEvent event)
  { 
    log.info("selectResearchProject22" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject22 = em.find(ResearchProject.class, id);
      addResearchProject5(researchProject22);
    }
  }

  @DataModel("researchProject22List") private Map<String, String> researchProject22List;

  public Map<String, String> getResearchProject22List()
  { 
    return researchProject22List;
  }

  @Factory("researchProject22List") public void initResearchProject22List()
  { 
    log.info("initResearchProject22List");
    researchProject22List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject22List.put(p.getName(), p.getId().toString());
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