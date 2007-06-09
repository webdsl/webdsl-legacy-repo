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
    initPerson44List();
    initPublication10List();
    initPublication13List();
    initPerson1040List();
    initProject1140List();
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

  public void removePerson3(Person person43)
  { 
    this.getResearchProject().getMembers().remove(person43);
  }

  public void addPerson3(Person person43)
  { 
    this.getResearchProject().getMembers().add(person43);
  }

  public void setPublication3(Publication publication11)
  { 
    researchProject.setProposal(publication11);
  }

  public void removePublication0(Publication publication12)
  { 
    this.getResearchProject().getPublications().remove(publication12);
  }

  public void addPublication0(Publication publication12)
  { 
    this.getResearchProject().getPublications().add(publication12);
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

  private String newPerson44;

  public void setNewPerson44(String p)
  { 
    newPerson44 = p;
  }

  public String getNewPerson44()
  { 
    return newPerson44;
  }

  public void selectPerson44(ValueChangeEvent event)
  { 
    log.info("selectPerson44" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person44 = em.find(Person.class, id);
      addPerson3(person44);
    }
  }

  @DataModel("person44List") private Map<String, String> person44List;

  public Map<String, String> getPerson44List()
  { 
    return person44List;
  }

  @Factory("person44List") public void initPerson44List()
  { 
    log.info("initPerson44List");
    person44List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person44List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication10;

  public void setNewPublication10(String p)
  { 
    newPublication10 = p;
  }

  public String getNewPublication10()
  { 
    return newPublication10;
  }

  public void selectPublication10(ValueChangeEvent event)
  { 
    log.info("selectPublication10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication10 = em.find(Publication.class, id);
      setPublication3(publication10);
    }
  }

  @DataModel("publication10List") private Map<String, String> publication10List;

  public Map<String, String> getPublication10List()
  { 
    return publication10List;
  }

  @Factory("publication10List") public void initPublication10List()
  { 
    log.info("initPublication10List");
    publication10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication10List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication13;

  public void setNewPublication13(String p)
  { 
    newPublication13 = p;
  }

  public String getNewPublication13()
  { 
    return newPublication13;
  }

  public void selectPublication13(ValueChangeEvent event)
  { 
    log.info("selectPublication13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication13 = em.find(Publication.class, id);
      addPublication0(publication13);
    }
  }

  @DataModel("publication13List") private Map<String, String> publication13List;

  public Map<String, String> getPublication13List()
  { 
    return publication13List;
  }

  @Factory("publication13List") public void initPublication13List()
  { 
    log.info("initPublication13List");
    publication13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication13List.put(p.getName(), p.getId().toString());
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
}