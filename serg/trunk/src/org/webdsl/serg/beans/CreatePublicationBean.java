package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
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
    Publication var33 = new Publication();
    publication = var33;
    initPerson5List();
    initResearchProject3List();
    initPerson10List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson1(Person person4)
  { 
    this.getPublication().getAuthors().remove(person4);
  }

  public void addPerson1(Person person4)
  { 
    this.getPublication().getAuthors().add(person4);
  }

  public void removeResearchProject1(ResearchProject researchProject2)
  { 
    this.getPublication().getProjectsList().remove(researchProject2);
  }

  public void addResearchProject1(ResearchProject researchProject2)
  { 
    this.getPublication().getProjectsList().add(researchProject2);
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

  private String newPerson5;

  public void setNewPerson5(String p)
  { 
    newPerson5 = p;
  }

  public String getNewPerson5()
  { 
    return newPerson5;
  }

  public String selectPerson5()
  { 
    log.info("selectPerson5" + " " + newPerson5);
    Person person5 = em.find(Person.class, new Long(newPerson5));
    addPerson1(person5);
    return null;
  }

  @DataModel("person5List") private Map<String, String> person5List;

  public Map<String, String> getPerson5List()
  { 
    return person5List;
  }

  @Factory("person5List") public void initPerson5List()
  { 
    log.info("initPerson5List");
    person5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person5List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject3;

  public void setNewResearchProject3(String p)
  { 
    newResearchProject3 = p;
  }

  public String getNewResearchProject3()
  { 
    return newResearchProject3;
  }

  public String selectResearchProject3()
  { 
    log.info("selectResearchProject3" + " " + newResearchProject3);
    ResearchProject researchProject3 = em.find(ResearchProject.class, new Long(newResearchProject3));
    addResearchProject1(researchProject3);
    return null;
  }

  @DataModel("researchProject3List") private Map<String, String> researchProject3List;

  public Map<String, String> getResearchProject3List()
  { 
    return researchProject3List;
  }

  @Factory("researchProject3List") public void initResearchProject3List()
  { 
    log.info("initResearchProject3List");
    researchProject3List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject3List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person10List") private List<Person> person10List;

  public List<Person> getPerson10List()
  { 
    log.info("getPerson10List");
    return person10List;
  }

  @Factory("person10List") public void initPerson10List()
  { 
    log.info("initPerson10List");
    person10List = em.createQuery("from " + "Person").getResultList();
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
}