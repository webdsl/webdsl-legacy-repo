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

@Stateful @Name("createResearchProject") public class CreateResearchProjectBean  implements CreateResearchProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createResearchProject" + ".initalize()");
    ResearchProject var57 = new ResearchProject();
    researchProject = var57;
    initPerson180List();
    initPublication24List();
    initPublication27List();
    initPerson87List();
    initProject77List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson11(Person person179)
  { 
    this.getResearchProject().getMembers().remove(person179);
  }

  public void addPerson11(Person person179)
  { 
    this.getResearchProject().getMembers().add(person179);
  }

  public void setPublication3(Publication publication25)
  { 
    researchProject.setProposal(publication25);
  }

  public void removePublication1(Publication publication26)
  { 
    this.getResearchProject().getPublications().remove(publication26);
  }

  public void addPublication1(Publication publication26)
  { 
    this.getResearchProject().getPublications().add(publication26);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getResearchProject());
    return "/" + "viewResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject.getId() + "");
  }

  private String newPerson180;

  public void setNewPerson180(String p)
  { 
    newPerson180 = p;
  }

  public String getNewPerson180()
  { 
    return newPerson180;
  }

  public void selectPerson180(ValueChangeEvent event)
  { 
    log.info("selectPerson180" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person180 = em.find(Person.class, id);
      addPerson11(person180);
    }
  }

  @DataModel("person180List") private Map<String, String> person180List;

  public Map<String, String> getPerson180List()
  { 
    return person180List;
  }

  @Factory("person180List") public void initPerson180List()
  { 
    log.info("initPerson180List");
    person180List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person180List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication24;

  public void setNewPublication24(String p)
  { 
    newPublication24 = p;
  }

  public String getNewPublication24()
  { 
    return newPublication24;
  }

  public void selectPublication24(ValueChangeEvent event)
  { 
    log.info("selectPublication24" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication24 = em.find(Publication.class, id);
      setPublication3(publication24);
    }
  }

  @DataModel("publication24List") private Map<String, String> publication24List;

  public Map<String, String> getPublication24List()
  { 
    return publication24List;
  }

  @Factory("publication24List") public void initPublication24List()
  { 
    log.info("initPublication24List");
    publication24List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication24List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication27;

  public void setNewPublication27(String p)
  { 
    newPublication27 = p;
  }

  public String getNewPublication27()
  { 
    return newPublication27;
  }

  public void selectPublication27(ValueChangeEvent event)
  { 
    log.info("selectPublication27" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication27 = em.find(Publication.class, id);
      addPublication1(publication27);
    }
  }

  @DataModel("publication27List") private Map<String, String> publication27List;

  public Map<String, String> getPublication27List()
  { 
    return publication27List;
  }

  @Factory("publication27List") public void initPublication27List()
  { 
    log.info("initPublication27List");
    publication27List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication27List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person87List") private List<Person> person87List;

  public List<Person> getPerson87List()
  { 
    log.info("getPerson87List");
    return person87List;
  }

  @Factory("person87List") public void initPerson87List()
  { 
    log.info("initPerson87List");
    person87List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project77List") private List<ResearchProject> project77List;

  public List<ResearchProject> getProject77List()
  { 
    log.info("getProject77List");
    return project77List;
  }

  @Factory("project77List") public void initProject77List()
  { 
    log.info("initProject77List");
    project77List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private ResearchProject researchProject;

  public ResearchProject getResearchProject()
  { 
    log.info("getResearchProject");
    return researchProject;
  }

  public void setResearchProject(ResearchProject researchProject)
  { 
    log.info("setResearchProject");
    this.researchProject = researchProject;
  }
}