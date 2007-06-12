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
    initPerson173List();
    initPublication25List();
    initPublication28List();
    initPerson81List();
    initProject76List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson13(Person person172)
  { 
    this.getResearchProject().getMembers().remove(person172);
  }

  public void addPerson13(Person person172)
  { 
    this.getResearchProject().getMembers().add(person172);
  }

  public void setPublication4(Publication publication26)
  { 
    researchProject.setProposal(publication26);
  }

  public void removePublication1(Publication publication27)
  { 
    this.getResearchProject().getPublications().remove(publication27);
  }

  public void addPublication1(Publication publication27)
  { 
    this.getResearchProject().getPublications().add(publication27);
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

  private String newPerson173;

  public void setNewPerson173(String p)
  { 
    newPerson173 = p;
  }

  public String getNewPerson173()
  { 
    return newPerson173;
  }

  public void selectPerson173(ValueChangeEvent event)
  { 
    log.info("selectPerson173" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person173 = em.find(Person.class, id);
      addPerson13(person173);
    }
  }

  @DataModel("person173List") private Map<String, String> person173List;

  public Map<String, String> getPerson173List()
  { 
    return person173List;
  }

  @Factory("person173List") public void initPerson173List()
  { 
    log.info("initPerson173List");
    person173List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person173List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication25;

  public void setNewPublication25(String p)
  { 
    newPublication25 = p;
  }

  public String getNewPublication25()
  { 
    return newPublication25;
  }

  public void selectPublication25(ValueChangeEvent event)
  { 
    log.info("selectPublication25" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication25 = em.find(Publication.class, id);
      setPublication4(publication25);
    }
  }

  @DataModel("publication25List") private Map<String, String> publication25List;

  public Map<String, String> getPublication25List()
  { 
    return publication25List;
  }

  @Factory("publication25List") public void initPublication25List()
  { 
    log.info("initPublication25List");
    publication25List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication25List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication28;

  public void setNewPublication28(String p)
  { 
    newPublication28 = p;
  }

  public String getNewPublication28()
  { 
    return newPublication28;
  }

  public void selectPublication28(ValueChangeEvent event)
  { 
    log.info("selectPublication28" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication28 = em.find(Publication.class, id);
      addPublication1(publication28);
    }
  }

  @DataModel("publication28List") private Map<String, String> publication28List;

  public Map<String, String> getPublication28List()
  { 
    return publication28List;
  }

  @Factory("publication28List") public void initPublication28List()
  { 
    log.info("initPublication28List");
    publication28List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication28List.put(p.getName(), p.getId().toString());
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