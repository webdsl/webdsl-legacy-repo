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
    initPerson217List();
    initPublication30List();
    initPublication33List();
    initPerson95List();
    initProject82List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson13(Person person216)
  { 
    this.getResearchProject().getMembers().remove(person216);
  }

  public void addPerson13(Person person216)
  { 
    this.getResearchProject().getMembers().add(person216);
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

  private String newPerson217;

  public void setNewPerson217(String p)
  { 
    newPerson217 = p;
  }

  public String getNewPerson217()
  { 
    return newPerson217;
  }

  public void selectPerson217(ValueChangeEvent event)
  { 
    log.info("selectPerson217" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person217 = em.find(Person.class, id);
      addPerson13(person217);
    }
  }

  @DataModel("person217List") private Map<String, String> person217List;

  public Map<String, String> getPerson217List()
  { 
    return person217List;
  }

  @Factory("person217List") public void initPerson217List()
  { 
    log.info("initPerson217List");
    person217List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person217List.put(p.getName(), p.getId().toString());
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication30 = em.find(Publication.class, id);
      setPublication3(publication30);
    }
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication33 = em.find(Publication.class, id);
      addPublication1(publication33);
    }
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

  @DataModel("person95List") private List<Person> person95List;

  public List<Person> getPerson95List()
  { 
    log.info("getPerson95List");
    return person95List;
  }

  @Factory("person95List") public void initPerson95List()
  { 
    log.info("initPerson95List");
    person95List = em.createQuery("from " + "Person").getResultList();
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