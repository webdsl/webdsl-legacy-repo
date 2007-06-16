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
    initPerson190List();
    initPublication26List();
    initPublication29List();
    initPerson87List();
    initProject77List();
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

  public void removePerson10(Person person189)
  { 
    this.getResearchProject().getMembers().remove(person189);
  }

  public void addPerson10(Person person189)
  { 
    this.getResearchProject().getMembers().add(person189);
  }

  public void setPublication2(Publication publication27)
  { 
    researchProject.setProposal(publication27);
  }

  public void removePublication0(Publication publication28)
  { 
    this.getResearchProject().getPublications().remove(publication28);
  }

  public void addPublication0(Publication publication28)
  { 
    this.getResearchProject().getPublications().add(publication28);
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

  private String newPerson190;

  public void setNewPerson190(String p)
  { 
    newPerson190 = p;
  }

  public String getNewPerson190()
  { 
    return newPerson190;
  }

  public void selectPerson190(ValueChangeEvent event)
  { 
    log.info("selectPerson190" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person190 = em.find(Person.class, id);
      addPerson10(person190);
    }
  }

  @DataModel("person190List") private Map<String, String> person190List;

  public Map<String, String> getPerson190List()
  { 
    return person190List;
  }

  @Factory("person190List") public void initPerson190List()
  { 
    log.info("initPerson190List");
    person190List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person190List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication26;

  public void setNewPublication26(String p)
  { 
    newPublication26 = p;
  }

  public String getNewPublication26()
  { 
    return newPublication26;
  }

  public void selectPublication26(ValueChangeEvent event)
  { 
    log.info("selectPublication26" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication26 = em.find(Publication.class, id);
      setPublication2(publication26);
    }
  }

  @DataModel("publication26List") private Map<String, String> publication26List;

  public Map<String, String> getPublication26List()
  { 
    return publication26List;
  }

  @Factory("publication26List") public void initPublication26List()
  { 
    log.info("initPublication26List");
    publication26List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication26List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication29;

  public void setNewPublication29(String p)
  { 
    newPublication29 = p;
  }

  public String getNewPublication29()
  { 
    return newPublication29;
  }

  public void selectPublication29(ValueChangeEvent event)
  { 
    log.info("selectPublication29" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication29 = em.find(Publication.class, id);
      addPublication0(publication29);
    }
  }

  @DataModel("publication29List") private Map<String, String> publication29List;

  public Map<String, String> getPublication29List()
  { 
    return publication29List;
  }

  @Factory("publication29List") public void initPublication29List()
  { 
    log.info("initPublication29List");
    publication29List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication29List.put(p.getName(), p.getId().toString());
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
}