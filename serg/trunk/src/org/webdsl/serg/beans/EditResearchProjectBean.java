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
    initPerson178List();
    initPublication20List();
    initPublication23List();
    initPerson86List();
    initProject76List();
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

  public void removePerson10(Person person177)
  { 
    this.getResearchProject().getMembers().remove(person177);
  }

  public void addPerson10(Person person177)
  { 
    this.getResearchProject().getMembers().add(person177);
  }

  public void setPublication2(Publication publication21)
  { 
    researchProject.setProposal(publication21);
  }

  public void removePublication0(Publication publication22)
  { 
    this.getResearchProject().getPublications().remove(publication22);
  }

  public void addPublication0(Publication publication22)
  { 
    this.getResearchProject().getPublications().add(publication22);
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

  private String newPerson178;

  public void setNewPerson178(String p)
  { 
    newPerson178 = p;
  }

  public String getNewPerson178()
  { 
    return newPerson178;
  }

  public void selectPerson178(ValueChangeEvent event)
  { 
    log.info("selectPerson178" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person178 = em.find(Person.class, id);
      addPerson10(person178);
    }
  }

  @DataModel("person178List") private Map<String, String> person178List;

  public Map<String, String> getPerson178List()
  { 
    return person178List;
  }

  @Factory("person178List") public void initPerson178List()
  { 
    log.info("initPerson178List");
    person178List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person178List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication20;

  public void setNewPublication20(String p)
  { 
    newPublication20 = p;
  }

  public String getNewPublication20()
  { 
    return newPublication20;
  }

  public void selectPublication20(ValueChangeEvent event)
  { 
    log.info("selectPublication20" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication20 = em.find(Publication.class, id);
      setPublication2(publication20);
    }
  }

  @DataModel("publication20List") private Map<String, String> publication20List;

  public Map<String, String> getPublication20List()
  { 
    return publication20List;
  }

  @Factory("publication20List") public void initPublication20List()
  { 
    log.info("initPublication20List");
    publication20List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication20List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication23;

  public void setNewPublication23(String p)
  { 
    newPublication23 = p;
  }

  public String getNewPublication23()
  { 
    return newPublication23;
  }

  public void selectPublication23(ValueChangeEvent event)
  { 
    log.info("selectPublication23" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication23 = em.find(Publication.class, id);
      addPublication0(publication23);
    }
  }

  @DataModel("publication23List") private Map<String, String> publication23List;

  public Map<String, String> getPublication23List()
  { 
    return publication23List;
  }

  @Factory("publication23List") public void initPublication23List()
  { 
    log.info("initPublication23List");
    publication23List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication23List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person86List") private List<Person> person86List;

  public List<Person> getPerson86List()
  { 
    log.info("getPerson86List");
    return person86List;
  }

  @Factory("person86List") public void initPerson86List()
  { 
    log.info("initPerson86List");
    person86List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project76List") private List<ResearchProject> project76List;

  public List<ResearchProject> getProject76List()
  { 
    log.info("getProject76List");
    return project76List;
  }

  @Factory("project76List") public void initProject76List()
  { 
    log.info("initProject76List");
    project76List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}