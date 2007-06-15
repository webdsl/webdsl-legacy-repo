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
    ResearchProject var49 = new ResearchProject();
    researchProject = var49;
    initPerson176List();
    initPublication23List();
    initPublication26List();
    initPerson83List();
    initProject78List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson11(Person person175)
  { 
    this.getResearchProject().getMembers().remove(person175);
  }

  public void addPerson11(Person person175)
  { 
    this.getResearchProject().getMembers().add(person175);
  }

  public void setPublication3(Publication publication24)
  { 
    researchProject.setProposal(publication24);
  }

  public void removePublication1(Publication publication25)
  { 
    this.getResearchProject().getPublications().remove(publication25);
  }

  public void addPublication1(Publication publication25)
  { 
    this.getResearchProject().getPublications().add(publication25);
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

  private String newPerson176;

  public void setNewPerson176(String p)
  { 
    newPerson176 = p;
  }

  public String getNewPerson176()
  { 
    return newPerson176;
  }

  public void selectPerson176(ValueChangeEvent event)
  { 
    log.info("selectPerson176" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person176 = em.find(Person.class, id);
      addPerson11(person176);
    }
  }

  @DataModel("person176List") private Map<String, String> person176List;

  public Map<String, String> getPerson176List()
  { 
    return person176List;
  }

  @Factory("person176List") public void initPerson176List()
  { 
    log.info("initPerson176List");
    person176List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person176List.put(p.getName(), p.getId().toString());
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
      setPublication3(publication23);
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
      addPublication1(publication26);
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

  @DataModel("person83List") private List<Person> person83List;

  public List<Person> getPerson83List()
  { 
    log.info("getPerson83List");
    return person83List;
  }

  @Factory("person83List") public void initPerson83List()
  { 
    log.info("initPerson83List");
    person83List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project78List") private List<ResearchProject> project78List;

  public List<ResearchProject> getProject78List()
  { 
    log.info("getProject78List");
    return project78List;
  }

  @Factory("project78List") public void initProject78List()
  { 
    log.info("initProject78List");
    project78List = em.createQuery("from " + "ResearchProject").getResultList();
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