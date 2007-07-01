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
    Person var32 = new Person();
    newAuthor0 = var32;
    initPerson184List();
    initResearchProject26List();
    initPerson66List();
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

  public void removePerson0(Person person183)
  { 
    this.getPublication().getAuthors().remove(person183);
  }

  public void addPerson0(Person person183)
  { 
    this.getPublication().getAuthors().add(person183);
  }

  public void addNewAuthor()
  { 
    this.getPublication().getAuthors().add(this.getNewAuthor0());
    Person var31 = new Person();
    newAuthor0 = var31;
  }

  public void removeResearchProject4(ResearchProject researchProject25)
  { 
    this.getPublication().getProjects().remove(researchProject25);
  }

  public void addResearchProject4(ResearchProject researchProject25)
  { 
    this.getPublication().getProjects().add(researchProject25);
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

  private String newPerson184;

  public void setNewPerson184(String p)
  { 
    newPerson184 = p;
  }

  public String getNewPerson184()
  { 
    return newPerson184;
  }

  public void selectPerson184(ValueChangeEvent event)
  { 
    log.info("selectPerson184" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person184 = em.find(Person.class, id);
      addPerson0(person184);
    }
  }

  @DataModel("person184List") private Map<String, String> person184List;

  public Map<String, String> getPerson184List()
  { 
    return person184List;
  }

  @Factory("person184List") public void initPerson184List()
  { 
    log.info("initPerson184List");
    person184List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person184List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject26;

  public void setNewResearchProject26(String p)
  { 
    newResearchProject26 = p;
  }

  public String getNewResearchProject26()
  { 
    return newResearchProject26;
  }

  public void selectResearchProject26(ValueChangeEvent event)
  { 
    log.info("selectResearchProject26" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject26 = em.find(ResearchProject.class, id);
      addResearchProject4(researchProject26);
    }
  }

  @DataModel("researchProject26List") private Map<String, String> researchProject26List;

  public Map<String, String> getResearchProject26List()
  { 
    return researchProject26List;
  }

  @Factory("researchProject26List") public void initResearchProject26List()
  { 
    log.info("initResearchProject26List");
    researchProject26List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject26List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person66List") private List<Person> person66List;

  public List<Person> getPerson66List()
  { 
    log.info("getPerson66List");
    return person66List;
  }

  @Factory("person66List") public void initPerson66List()
  { 
    log.info("initPerson66List");
    person66List = em.createQuery("from " + "Person").getResultList();
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