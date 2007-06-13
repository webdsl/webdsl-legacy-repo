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
    initPerson156List();
    initPublication19List();
    initPublication22List();
    initPerson81List();
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

  public void removePerson10(Person person155)
  { 
    this.getResearchProject().getMembers().remove(person155);
  }

  public void addPerson10(Person person155)
  { 
    this.getResearchProject().getMembers().add(person155);
  }

  public void setPublication2(Publication publication20)
  { 
    researchProject.setProposal(publication20);
  }

  public void removePublication0(Publication publication21)
  { 
    this.getResearchProject().getPublications().remove(publication21);
  }

  public void addPublication0(Publication publication21)
  { 
    this.getResearchProject().getPublications().add(publication21);
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

  private String newPerson156;

  public void setNewPerson156(String p)
  { 
    newPerson156 = p;
  }

  public String getNewPerson156()
  { 
    return newPerson156;
  }

  public void selectPerson156(ValueChangeEvent event)
  { 
    log.info("selectPerson156" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person156 = em.find(Person.class, id);
      addPerson10(person156);
    }
  }

  @DataModel("person156List") private Map<String, String> person156List;

  public Map<String, String> getPerson156List()
  { 
    return person156List;
  }

  @Factory("person156List") public void initPerson156List()
  { 
    log.info("initPerson156List");
    person156List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person156List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication19;

  public void setNewPublication19(String p)
  { 
    newPublication19 = p;
  }

  public String getNewPublication19()
  { 
    return newPublication19;
  }

  public void selectPublication19(ValueChangeEvent event)
  { 
    log.info("selectPublication19" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication19 = em.find(Publication.class, id);
      setPublication2(publication19);
    }
  }

  @DataModel("publication19List") private Map<String, String> publication19List;

  public Map<String, String> getPublication19List()
  { 
    return publication19List;
  }

  @Factory("publication19List") public void initPublication19List()
  { 
    log.info("initPublication19List");
    publication19List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication19List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication22;

  public void setNewPublication22(String p)
  { 
    newPublication22 = p;
  }

  public String getNewPublication22()
  { 
    return newPublication22;
  }

  public void selectPublication22(ValueChangeEvent event)
  { 
    log.info("selectPublication22" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication22 = em.find(Publication.class, id);
      addPublication0(publication22);
    }
  }

  @DataModel("publication22List") private Map<String, String> publication22List;

  public Map<String, String> getPublication22List()
  { 
    return publication22List;
  }

  @Factory("publication22List") public void initPublication22List()
  { 
    log.info("initPublication22List");
    publication22List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication22List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person81List") private List<Person> person81List;

  public List<Person> getPerson81List()
  { 
    log.info("getPerson81List");
    return person81List;
  }

  @Factory("person81List") public void initPerson81List()
  { 
    log.info("initPerson81List");
    person81List = em.createQuery("from " + "Person").getResultList();
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