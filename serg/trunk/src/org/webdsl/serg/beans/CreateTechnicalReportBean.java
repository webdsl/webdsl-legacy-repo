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

@Stateful @Name("createTechnicalReport") public class CreateTechnicalReportBean  implements CreateTechnicalReportBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createTechnicalReport" + ".initalize()");
    TechnicalReport var36 = new TechnicalReport();
    technicalReport = var36;
    Person var37 = new Person();
    newAuthor3 = var37;
    initPerson160List();
    initResearchProject27List();
    initPublication18List();
    initPerson63List();
    initProject57List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson3(Person person159)
  { 
    this.getTechnicalReport().getAuthors().remove(person159);
  }

  public void addPerson3(Person person159)
  { 
    this.getTechnicalReport().getAuthors().add(person159);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor3());
    Person var35 = new Person();
    newAuthor3 = var35;
  }

  public void removeResearchProject7(ResearchProject researchProject26)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject26);
  }

  public void addResearchProject7(ResearchProject researchProject26)
  { 
    this.getTechnicalReport().getProjects().add(researchProject26);
  }

  public void setPublication1(Publication publication19)
  { 
    technicalReport.setPreprintof(publication19);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getTechnicalReport());
    return "/" + "viewTechnicalReport" + ".seam?" + ("technicalReport" + "=" + technicalReport.getId() + "");
  }

  private String newPerson160;

  public void setNewPerson160(String p)
  { 
    newPerson160 = p;
  }

  public String getNewPerson160()
  { 
    return newPerson160;
  }

  public void selectPerson160(ValueChangeEvent event)
  { 
    log.info("selectPerson160" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person160 = em.find(Person.class, id);
      addPerson3(person160);
    }
  }

  @DataModel("person160List") private Map<String, String> person160List;

  public Map<String, String> getPerson160List()
  { 
    return person160List;
  }

  @Factory("person160List") public void initPerson160List()
  { 
    log.info("initPerson160List");
    person160List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person160List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject27;

  public void setNewResearchProject27(String p)
  { 
    newResearchProject27 = p;
  }

  public String getNewResearchProject27()
  { 
    return newResearchProject27;
  }

  public void selectResearchProject27(ValueChangeEvent event)
  { 
    log.info("selectResearchProject27" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject27 = em.find(ResearchProject.class, id);
      addResearchProject7(researchProject27);
    }
  }

  @DataModel("researchProject27List") private Map<String, String> researchProject27List;

  public Map<String, String> getResearchProject27List()
  { 
    return researchProject27List;
  }

  @Factory("researchProject27List") public void initResearchProject27List()
  { 
    log.info("initResearchProject27List");
    researchProject27List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject27List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication18;

  public void setNewPublication18(String p)
  { 
    newPublication18 = p;
  }

  public String getNewPublication18()
  { 
    return newPublication18;
  }

  public void selectPublication18(ValueChangeEvent event)
  { 
    log.info("selectPublication18" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication18 = em.find(Publication.class, id);
      setPublication1(publication18);
    }
  }

  @DataModel("publication18List") private Map<String, String> publication18List;

  public Map<String, String> getPublication18List()
  { 
    return publication18List;
  }

  @Factory("publication18List") public void initPublication18List()
  { 
    log.info("initPublication18List");
    publication18List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication18List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person63List") private List<Person> person63List;

  public List<Person> getPerson63List()
  { 
    log.info("getPerson63List");
    return person63List;
  }

  @Factory("person63List") public void initPerson63List()
  { 
    log.info("initPerson63List");
    person63List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project57List") private List<ResearchProject> project57List;

  public List<ResearchProject> getProject57List()
  { 
    log.info("getProject57List");
    return project57List;
  }

  @Factory("project57List") public void initProject57List()
  { 
    log.info("initProject57List");
    project57List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private TechnicalReport technicalReport;

  public TechnicalReport getTechnicalReport()
  { 
    log.info("getTechnicalReport");
    return technicalReport;
  }

  public void setTechnicalReport(TechnicalReport technicalReport)
  { 
    log.info("setTechnicalReport");
    this.technicalReport = technicalReport;
  }

  private Person newAuthor3;

  public Person getNewAuthor3()
  { 
    log.info("getNewAuthor3");
    return newAuthor3;
  }

  public void setNewAuthor3(Person newAuthor3)
  { 
    log.info("setNewAuthor3");
    this.newAuthor3 = newAuthor3;
  }
}