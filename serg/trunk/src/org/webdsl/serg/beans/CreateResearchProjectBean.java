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
    ResearchProject var35 = new ResearchProject();
    researchProject = var35;
    initPerson57List();
    initPublication17List();
    initPublication20List();
    initPerson1041List();
    initProject1141List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson6(Person person56)
  { 
    this.getResearchProject().getMembers().remove(person56);
  }

  public void addPerson6(Person person56)
  { 
    this.getResearchProject().getMembers().add(person56);
  }

  public void setPublication4(Publication publication18)
  { 
    researchProject.setProposal(publication18);
  }

  public void removePublication1(Publication publication19)
  { 
    this.getResearchProject().getPublications().remove(publication19);
  }

  public void addPublication1(Publication publication19)
  { 
    this.getResearchProject().getPublications().add(publication19);
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

  private String newPerson57;

  public void setNewPerson57(String p)
  { 
    newPerson57 = p;
  }

  public String getNewPerson57()
  { 
    return newPerson57;
  }

  public void selectPerson57(ValueChangeEvent event)
  { 
    log.info("selectPerson57" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person57 = em.find(Person.class, id);
      addPerson6(person57);
    }
  }

  @DataModel("person57List") private Map<String, String> person57List;

  public Map<String, String> getPerson57List()
  { 
    return person57List;
  }

  @Factory("person57List") public void initPerson57List()
  { 
    log.info("initPerson57List");
    person57List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person57List.put(p.getName(), p.getId().toString());
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
      setPublication4(publication17);
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
      addPublication1(publication20);
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

  @DataModel("person1041List") private List<Person> person1041List;

  public List<Person> getPerson1041List()
  { 
    log.info("getPerson1041List");
    return person1041List;
  }

  @Factory("person1041List") public void initPerson1041List()
  { 
    log.info("initPerson1041List");
    person1041List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1141List") private List<ResearchProject> project1141List;

  public List<ResearchProject> getProject1141List()
  { 
    log.info("getProject1141List");
    return project1141List;
  }

  @Factory("project1141List") public void initProject1141List()
  { 
    log.info("initProject1141List");
    project1141List = em.createQuery("from " + "ResearchProject").getResultList();
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