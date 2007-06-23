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
    ResearchProject var65 = new ResearchProject();
    researchProject = var65;
    initPerson202List();
    initPublication30List();
    initPublication33List();
    initPerson93List();
    initProject82List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson13(Person person201)
  { 
    this.getResearchProject().getMembers().remove(person201);
  }

  public void addPerson13(Person person201)
  { 
    this.getResearchProject().getMembers().add(person201);
  }

  public void setPublication3(Publication publication31)
  { 
    researchProject.setProposal(publication31);
  }

  public void removePublication1(Publication publication32)
  { 
    this.getResearchProject().getPublications().remove(publication32);
  }

  public void addPublication1(Publication publication32)
  { 
    this.getResearchProject().getPublications().add(publication32);
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

  private String newPerson202;

  public void setNewPerson202(String p)
  { 
    newPerson202 = p;
  }

  public String getNewPerson202()
  { 
    return newPerson202;
  }

  public void selectPerson202(ValueChangeEvent event)
  { 
    log.info("selectPerson202" + ": new value = " + " " + event.getNewValue());
    Person person202 = (Person)event.getNewValue();
  }

  @DataModel("person202List") private Map<String, String> person202List;

  public Map<String, String> getPerson202List()
  { 
    return person202List;
  }

  @Factory("person202List") public void initPerson202List()
  { 
    log.info("initPerson202List");
    person202List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person202List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication30;

  public void setNewPublication30(String p)
  { 
    newPublication30 = p;
  }

  public String getNewPublication30()
  { 
    return newPublication30;
  }

  public void selectPublication30(ValueChangeEvent event)
  { 
    log.info("selectPublication30" + ": new value = " + " " + event.getNewValue());
    Publication publication30 = (Publication)event.getNewValue();
  }

  @DataModel("publication30List") private Map<String, String> publication30List;

  public Map<String, String> getPublication30List()
  { 
    return publication30List;
  }

  @Factory("publication30List") public void initPublication30List()
  { 
    log.info("initPublication30List");
    publication30List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication30List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication33;

  public void setNewPublication33(String p)
  { 
    newPublication33 = p;
  }

  public String getNewPublication33()
  { 
    return newPublication33;
  }

  public void selectPublication33(ValueChangeEvent event)
  { 
    log.info("selectPublication33" + ": new value = " + " " + event.getNewValue());
    Publication publication33 = (Publication)event.getNewValue();
  }

  @DataModel("publication33List") private Map<String, String> publication33List;

  public Map<String, String> getPublication33List()
  { 
    return publication33List;
  }

  @Factory("publication33List") public void initPublication33List()
  { 
    log.info("initPublication33List");
    publication33List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication33List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person93List") private List<Person> person93List;

  public List<Person> getPerson93List()
  { 
    log.info("getPerson93List");
    return person93List;
  }

  @Factory("person93List") public void initPerson93List()
  { 
    log.info("initPerson93List");
    person93List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project82List") private List<ResearchProject> project82List;

  public List<ResearchProject> getProject82List()
  { 
    log.info("getProject82List");
    return project82List;
  }

  @Factory("project82List") public void initProject82List()
  { 
    log.info("initProject82List");
    project82List = em.createQuery("from " + "ResearchProject").getResultList();
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