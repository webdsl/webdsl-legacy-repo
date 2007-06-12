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
    initPerson171List();
    initPublication21List();
    initPublication24List();
    initPerson80List();
    initProject75List();
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

  public void removePerson12(Person person170)
  { 
    this.getResearchProject().getMembers().remove(person170);
  }

  public void addPerson12(Person person170)
  { 
    this.getResearchProject().getMembers().add(person170);
  }

  public void setPublication3(Publication publication22)
  { 
    researchProject.setProposal(publication22);
  }

  public void removePublication0(Publication publication23)
  { 
    this.getResearchProject().getPublications().remove(publication23);
  }

  public void addPublication0(Publication publication23)
  { 
    this.getResearchProject().getPublications().add(publication23);
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

  private String newPerson171;

  public void setNewPerson171(String p)
  { 
    newPerson171 = p;
  }

  public String getNewPerson171()
  { 
    return newPerson171;
  }

  public void selectPerson171(ValueChangeEvent event)
  { 
    log.info("selectPerson171" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person171 = em.find(Person.class, id);
      addPerson12(person171);
    }
  }

  @DataModel("person171List") private Map<String, String> person171List;

  public Map<String, String> getPerson171List()
  { 
    return person171List;
  }

  @Factory("person171List") public void initPerson171List()
  { 
    log.info("initPerson171List");
    person171List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person171List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication21;

  public void setNewPublication21(String p)
  { 
    newPublication21 = p;
  }

  public String getNewPublication21()
  { 
    return newPublication21;
  }

  public void selectPublication21(ValueChangeEvent event)
  { 
    log.info("selectPublication21" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication21 = em.find(Publication.class, id);
      setPublication3(publication21);
    }
  }

  @DataModel("publication21List") private Map<String, String> publication21List;

  public Map<String, String> getPublication21List()
  { 
    return publication21List;
  }

  @Factory("publication21List") public void initPublication21List()
  { 
    log.info("initPublication21List");
    publication21List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication21List.put(p.getName(), p.getId().toString());
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
      addPublication0(publication24);
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

  @DataModel("person80List") private List<Person> person80List;

  public List<Person> getPerson80List()
  { 
    log.info("getPerson80List");
    return person80List;
  }

  @Factory("person80List") public void initPerson80List()
  { 
    log.info("initPerson80List");
    person80List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project75List") private List<ResearchProject> project75List;

  public List<ResearchProject> getProject75List()
  { 
    log.info("getProject75List");
    return project75List;
  }

  @Factory("project75List") public void initProject75List()
  { 
    log.info("initProject75List");
    project75List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}