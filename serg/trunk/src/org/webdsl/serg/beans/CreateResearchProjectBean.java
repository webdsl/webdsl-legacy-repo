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
    ResearchProject var31 = new ResearchProject();
    researchProject = var31;
    initPerson45List();
    initPublication14List();
    initPublication17List();
    initPerson1040List();
    initProject1140List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson4(Person person44)
  { 
    this.getResearchProject().getMembers().remove(person44);
  }

  public void addPerson4(Person person44)
  { 
    this.getResearchProject().getMembers().add(person44);
  }

  public void setPublication4(Publication publication15)
  { 
    researchProject.setProposal(publication15);
  }

  public void removePublication1(Publication publication16)
  { 
    this.getResearchProject().getPublications().remove(publication16);
  }

  public void addPublication1(Publication publication16)
  { 
    this.getResearchProject().getPublications().add(publication16);
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

  private String newPerson45;

  public void setNewPerson45(String p)
  { 
    newPerson45 = p;
  }

  public String getNewPerson45()
  { 
    return newPerson45;
  }

  public void selectPerson45(ValueChangeEvent event)
  { 
    log.info("selectPerson45" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person45 = em.find(Person.class, id);
      addPerson4(person45);
    }
  }

  @DataModel("person45List") private Map<String, String> person45List;

  public Map<String, String> getPerson45List()
  { 
    return person45List;
  }

  @Factory("person45List") public void initPerson45List()
  { 
    log.info("initPerson45List");
    person45List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person45List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication14;

  public void setNewPublication14(String p)
  { 
    newPublication14 = p;
  }

  public String getNewPublication14()
  { 
    return newPublication14;
  }

  public void selectPublication14(ValueChangeEvent event)
  { 
    log.info("selectPublication14" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication14 = em.find(Publication.class, id);
      setPublication4(publication14);
    }
  }

  @DataModel("publication14List") private Map<String, String> publication14List;

  public Map<String, String> getPublication14List()
  { 
    return publication14List;
  }

  @Factory("publication14List") public void initPublication14List()
  { 
    log.info("initPublication14List");
    publication14List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication14List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication17;

  public void setNewPublication17(String p)
  { 
    newPublication17 = p;
  }

  public String getNewPublication17()
  { 
    return newPublication17;
  }

  public void selectPublication17(ValueChangeEvent event)
  { 
    log.info("selectPublication17" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication17 = em.find(Publication.class, id);
      addPublication1(publication17);
    }
  }

  @DataModel("publication17List") private Map<String, String> publication17List;

  public Map<String, String> getPublication17List()
  { 
    return publication17List;
  }

  @Factory("publication17List") public void initPublication17List()
  { 
    log.info("initPublication17List");
    publication17List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication17List.put(p.getName(), p.getId().toString());
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