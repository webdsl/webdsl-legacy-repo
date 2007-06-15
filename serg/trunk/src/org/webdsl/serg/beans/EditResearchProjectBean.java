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
    initPerson174List();
    initPublication19List();
    initPublication22List();
    initPerson82List();
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

  public void removePerson10(Person person173)
  { 
    this.getResearchProject().getMembers().remove(person173);
  }

  public void addPerson10(Person person173)
  { 
    this.getResearchProject().getMembers().add(person173);
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

  private String newPerson174;

  public void setNewPerson174(String p)
  { 
    newPerson174 = p;
  }

  public String getNewPerson174()
  { 
    return newPerson174;
  }

  public void selectPerson174(ValueChangeEvent event)
  { 
    log.info("selectPerson174" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person174 = em.find(Person.class, id);
      addPerson10(person174);
    }
  }

  @DataModel("person174List") private Map<String, String> person174List;

  public Map<String, String> getPerson174List()
  { 
    return person174List;
  }

  @Factory("person174List") public void initPerson174List()
  { 
    log.info("initPerson174List");
    person174List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person174List.put(p.getName(), p.getId().toString());
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

  @DataModel("person82List") private List<Person> person82List;

  public List<Person> getPerson82List()
  { 
    log.info("getPerson82List");
    return person82List;
  }

  @Factory("person82List") public void initPerson82List()
  { 
    log.info("initPerson82List");
    person82List = em.createQuery("from " + "Person").getResultList();
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