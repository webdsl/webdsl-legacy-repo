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

@Stateful @Name("editResearchProject") public class EditResearchProjectBean  implements EditResearchProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editResearchProject" + ".initalize()");
    if(researchProjectId == null)
    { 
      log.info("No " + "researchProjectId" + " defined, creating new " + "ResearchProject");
      researchProject = new ResearchProject();
    }
    else
    { 
      researchProject = em.find(ResearchProject.class, researchProjectId);
    }
    initPerson55List();
    initPublication13List();
    initPublication16List();
    initPerson1040List();
    initProject1140List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("researchProject") private Long researchProjectId;

  private ResearchProject researchProject;

  public void setResearchProject(ResearchProject researchProject)
  { 
    log.info("setResearchProject");
    this.researchProject = researchProject;
  }

  public ResearchProject getResearchProject()
  { 
    log.info("getResearchProject");
    return researchProject;
  }

  public void removePerson5(Person person54)
  { 
    this.getResearchProject().getMembers().remove(person54);
  }

  public void addPerson5(Person person54)
  { 
    this.getResearchProject().getMembers().add(person54);
  }

  public void setPublication3(Publication publication14)
  { 
    researchProject.setProposal(publication14);
  }

  public void removePublication0(Publication publication15)
  { 
    this.getResearchProject().getPublications().remove(publication15);
  }

  public void addPublication0(Publication publication15)
  { 
    this.getResearchProject().getPublications().add(publication15);
  }

  @End public String cancel()
  { 
    return "/" + "viewResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getResearchProject());
    return "/" + "viewResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject.getId() + "");
  }

  private String newPerson55;

  public void setNewPerson55(String p)
  { 
    newPerson55 = p;
  }

  public String getNewPerson55()
  { 
    return newPerson55;
  }

  public void selectPerson55(ValueChangeEvent event)
  { 
    log.info("selectPerson55" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person55 = em.find(Person.class, id);
      addPerson5(person55);
    }
  }

  @DataModel("person55List") private Map<String, String> person55List;

  public Map<String, String> getPerson55List()
  { 
    return person55List;
  }

  @Factory("person55List") public void initPerson55List()
  { 
    log.info("initPerson55List");
    person55List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person55List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication13;

  public void setNewPublication13(String p)
  { 
    newPublication13 = p;
  }

  public String getNewPublication13()
  { 
    return newPublication13;
  }

  public void selectPublication13(ValueChangeEvent event)
  { 
    log.info("selectPublication13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication13 = em.find(Publication.class, id);
      setPublication3(publication13);
    }
  }

  @DataModel("publication13List") private Map<String, String> publication13List;

  public Map<String, String> getPublication13List()
  { 
    return publication13List;
  }

  @Factory("publication13List") public void initPublication13List()
  { 
    log.info("initPublication13List");
    publication13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication13List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication16;

  public void setNewPublication16(String p)
  { 
    newPublication16 = p;
  }

  public String getNewPublication16()
  { 
    return newPublication16;
  }

  public void selectPublication16(ValueChangeEvent event)
  { 
    log.info("selectPublication16" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication16 = em.find(Publication.class, id);
      addPublication0(publication16);
    }
  }

  @DataModel("publication16List") private Map<String, String> publication16List;

  public Map<String, String> getPublication16List()
  { 
    return publication16List;
  }

  @Factory("publication16List") public void initPublication16List()
  { 
    log.info("initPublication16List");
    publication16List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication16List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1040List") private List<Person> person1040List;

  public List<Person> getPerson1040List()
  { 
    log.info("getPerson1040List");
    return person1040List;
  }

  @Factory("person1040List") public void initPerson1040List()
  { 
    log.info("initPerson1040List");
    person1040List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1140List") private List<ResearchProject> project1140List;

  public List<ResearchProject> getProject1140List()
  { 
    log.info("getProject1140List");
    return project1140List;
  }

  @Factory("project1140List") public void initProject1140List()
  { 
    log.info("initProject1140List");
    project1140List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}