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
    initPerson200List();
    initPublication26List();
    initPublication29List();
    initPerson92List();
    initProject81List();
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

  public void removePerson12(Person person199)
  { 
    this.getResearchProject().getMembers().remove(person199);
  }

  public void addPerson12(Person person199)
  { 
    this.getResearchProject().getMembers().add(person199);
  }

  public void setPublication2(Publication publication27)
  { 
    researchProject.setProposal(publication27);
  }

  public void removePublication0(Publication publication28)
  { 
    this.getResearchProject().getPublications().remove(publication28);
  }

  public void addPublication0(Publication publication28)
  { 
    this.getResearchProject().getPublications().add(publication28);
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

  private String newPerson200;

  public void setNewPerson200(String p)
  { 
    newPerson200 = p;
  }

  public String getNewPerson200()
  { 
    return newPerson200;
  }

  public void selectPerson200(ValueChangeEvent event)
  { 
    log.info("selectPerson200" + ": new value = " + " " + event.getNewValue());
    Person person200 = (Person)event.getNewValue();
  }

  @DataModel("person200List") private Map<String, String> person200List;

  public Map<String, String> getPerson200List()
  { 
    return person200List;
  }

  @Factory("person200List") public void initPerson200List()
  { 
    log.info("initPerson200List");
    person200List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person200List.put(p.getName(), p.getId().toString());
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
    Publication publication26 = (Publication)event.getNewValue();
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

  private String newPublication29;

  public void setNewPublication29(String p)
  { 
    newPublication29 = p;
  }

  public String getNewPublication29()
  { 
    return newPublication29;
  }

  public void selectPublication29(ValueChangeEvent event)
  { 
    log.info("selectPublication29" + ": new value = " + " " + event.getNewValue());
    Publication publication29 = (Publication)event.getNewValue();
  }

  @DataModel("publication29List") private Map<String, String> publication29List;

  public Map<String, String> getPublication29List()
  { 
    return publication29List;
  }

  @Factory("publication29List") public void initPublication29List()
  { 
    log.info("initPublication29List");
    publication29List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication29List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person92List") private List<Person> person92List;

  public List<Person> getPerson92List()
  { 
    log.info("getPerson92List");
    return person92List;
  }

  @Factory("person92List") public void initPerson92List()
  { 
    log.info("initPerson92List");
    person92List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project81List") private List<ResearchProject> project81List;

  public List<ResearchProject> getProject81List()
  { 
    log.info("getProject81List");
    return project81List;
  }

  @Factory("project81List") public void initProject81List()
  { 
    log.info("initProject81List");
    project81List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}