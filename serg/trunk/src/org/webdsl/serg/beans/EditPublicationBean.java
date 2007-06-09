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
    initPerson38List();
    initResearchProject21List();
    initPerson1034List();
    initProject1134List();
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

  public void removePerson1(Person person37)
  { 
    this.getPublication().getAuthors().remove(person37);
  }

  public void addPerson1(Person person37)
  { 
    this.getPublication().getAuthors().add(person37);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor0());
    Person var25 = new Person();
    newAuthor0 = var25;
  }

  public void removeResearchProject7(ResearchProject researchProject20)
  { 
    this.getPublication().getProjects().remove(researchProject20);
  }

  public void addResearchProject7(ResearchProject researchProject20)
  { 
    this.getPublication().getProjects().add(researchProject20);
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

  private String newPerson38;

  public void setNewPerson38(String p)
  { 
    newPerson38 = p;
  }

  public String getNewPerson38()
  { 
    return newPerson38;
  }

  public void selectPerson38(ValueChangeEvent event)
  { 
    log.info("selectPerson38" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person38 = em.find(Person.class, id);
      addPerson1(person38);
    }
  }

  @DataModel("person38List") private Map<String, String> person38List;

  public Map<String, String> getPerson38List()
  { 
    return person38List;
  }

  @Factory("person38List") public void initPerson38List()
  { 
    log.info("initPerson38List");
    person38List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person38List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject21;

  public void setNewResearchProject21(String p)
  { 
    newResearchProject21 = p;
  }

  public String getNewResearchProject21()
  { 
    return newResearchProject21;
  }

  public void selectResearchProject21(ValueChangeEvent event)
  { 
    log.info("selectResearchProject21" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject21 = em.find(ResearchProject.class, id);
      addResearchProject7(researchProject21);
    }
  }

  @DataModel("researchProject21List") private Map<String, String> researchProject21List;

  public Map<String, String> getResearchProject21List()
  { 
    return researchProject21List;
  }

  @Factory("researchProject21List") public void initResearchProject21List()
  { 
    log.info("initResearchProject21List");
    researchProject21List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject21List.put(p.getName(), p.getId().toString());
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