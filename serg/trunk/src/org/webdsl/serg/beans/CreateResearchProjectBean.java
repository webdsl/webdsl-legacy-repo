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
    ResearchProject var47 = new ResearchProject();
    researchProject = var47;
    initPerson158List();
    initPublication23List();
    initPublication26List();
    initPerson82List();
    initProject77List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson11(Person person157)
  { 
    this.getResearchProject().getMembers().remove(person157);
  }

  public void addPerson11(Person person157)
  { 
    this.getResearchProject().getMembers().add(person157);
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

  private String newPerson158;

  public void setNewPerson158(String p)
  { 
    newPerson158 = p;
  }

  public String getNewPerson158()
  { 
    return newPerson158;
  }

  public void selectPerson158(ValueChangeEvent event)
  { 
    log.info("selectPerson158" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person158 = em.find(Person.class, id);
      addPerson11(person158);
    }
  }

  @DataModel("person158List") private Map<String, String> person158List;

  public Map<String, String> getPerson158List()
  { 
    return person158List;
  }

  @Factory("person158List") public void initPerson158List()
  { 
    log.info("initPerson158List");
    person158List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person158List.put(p.getName(), p.getId().toString());
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