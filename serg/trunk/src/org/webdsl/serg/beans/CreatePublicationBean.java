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
    initPerson39List();
    initResearchProject8List();
    initPerson1034List();
    initProject1134List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson2(Person person38)
  { 
    this.getPublication().getAuthors().remove(person38);
  }

  public void addPerson2(Person person38)
  { 
    this.getPublication().getAuthors().add(person38);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor1());
    Person var27 = new Person();
    newAuthor1 = var27;
  }

  public void removeResearchProject2(ResearchProject researchProject7)
  { 
    this.getPublication().getProjects().remove(researchProject7);
  }

  public void addResearchProject2(ResearchProject researchProject7)
  { 
    this.getPublication().getProjects().add(researchProject7);
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

  private String newPerson39;

  public void setNewPerson39(String p)
  { 
    newPerson39 = p;
  }

  public String getNewPerson39()
  { 
    return newPerson39;
  }

  public void selectPerson39(ValueChangeEvent event)
  { 
    log.info("selectPerson39" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person39 = em.find(Person.class, id);
      addPerson2(person39);
    }
  }

  @DataModel("person39List") private Map<String, String> person39List;

  public Map<String, String> getPerson39List()
  { 
    return person39List;
  }

  @Factory("person39List") public void initPerson39List()
  { 
    log.info("initPerson39List");
    person39List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person39List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject8;

  public void setNewResearchProject8(String p)
  { 
    newResearchProject8 = p;
  }

  public String getNewResearchProject8()
  { 
    return newResearchProject8;
  }

  public void selectResearchProject8(ValueChangeEvent event)
  { 
    log.info("selectResearchProject8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject8 = em.find(ResearchProject.class, id);
      addResearchProject2(researchProject8);
    }
  }

  @DataModel("researchProject8List") private Map<String, String> researchProject8List;

  public Map<String, String> getResearchProject8List()
  { 
    return researchProject8List;
  }

  @Factory("researchProject8List") public void initResearchProject8List()
  { 
    log.info("initResearchProject8List");
    researchProject8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject8List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1034List") private List<Person> person1034List;

  public List<Person> getPerson1034List()
  { 
    log.info("getPerson1034List");
    return person1034List;
  }

  @Factory("person1034List") public void initPerson1034List()
  { 
    log.info("initPerson1034List");
    person1034List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1134List") private List<ResearchProject> project1134List;

  public List<ResearchProject> getProject1134List()
  { 
    log.info("getProject1134List");
    return project1134List;
  }

  @Factory("project1134List") public void initProject1134List()
  { 
    log.info("initProject1134List");
    project1134List = em.createQuery("from " + "ResearchProject").getResultList();
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