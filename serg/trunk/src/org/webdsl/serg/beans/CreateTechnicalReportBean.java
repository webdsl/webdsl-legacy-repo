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
    TechnicalReport var41 = new TechnicalReport();
    technicalReport = var41;
    Person var42 = new Person();
    newAuthor3 = var42;
    initPerson172List();
    initResearchProject33List();
    initPublication24List();
    initPerson68List();
    initProject58List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson3(Person person171)
  { 
    this.getTechnicalReport().getAuthors().remove(person171);
  }

  public void addPerson3(Person person171)
  { 
    this.getTechnicalReport().getAuthors().add(person171);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor3());
    Person var40 = new Person();
    newAuthor3 = var40;
  }

  public void removeResearchProject7(ResearchProject researchProject32)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject32);
  }

  public void addResearchProject7(ResearchProject researchProject32)
  { 
    this.getTechnicalReport().getProjects().add(researchProject32);
  }

  public void setPublication1(Publication publication25)
  { 
    technicalReport.setPreprintof(publication25);
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

  private String newPerson172;

  public void setNewPerson172(String p)
  { 
    newPerson172 = p;
  }

  public String getNewPerson172()
  { 
    return newPerson172;
  }

  public void selectPerson172(ValueChangeEvent event)
  { 
    log.info("selectPerson172" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person172 = em.find(Person.class, id);
      addPerson3(person172);
    }
  }

  @DataModel("person172List") private Map<String, String> person172List;

  public Map<String, String> getPerson172List()
  { 
    return person172List;
  }

  @Factory("person172List") public void initPerson172List()
  { 
    log.info("initPerson172List");
    person172List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person172List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject33;

  public void setNewResearchProject33(String p)
  { 
    newResearchProject33 = p;
  }

  public String getNewResearchProject33()
  { 
    return newResearchProject33;
  }

  public void selectResearchProject33(ValueChangeEvent event)
  { 
    log.info("selectResearchProject33" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject33 = em.find(ResearchProject.class, id);
      addResearchProject7(researchProject33);
    }
  }

  @DataModel("researchProject33List") private Map<String, String> researchProject33List;

  public Map<String, String> getResearchProject33List()
  { 
    return researchProject33List;
  }

  @Factory("researchProject33List") public void initResearchProject33List()
  { 
    log.info("initResearchProject33List");
    researchProject33List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject33List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication24;

  public void setNewPublication24(String p)
  { 
    newPublication24 = p;
  }

  public String getNewPublication24()
  { 
    return newPublication24;
  }

  public void selectPublication24(ValueChangeEvent event)
  { 
    log.info("selectPublication24" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication24 = em.find(Publication.class, id);
      setPublication1(publication24);
    }
  }

  @DataModel("publication24List") private Map<String, String> publication24List;

  public Map<String, String> getPublication24List()
  { 
    return publication24List;
  }

  @Factory("publication24List") public void initPublication24List()
  { 
    log.info("initPublication24List");
    publication24List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication24List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person68List") private List<Person> person68List;

  public List<Person> getPerson68List()
  { 
    log.info("getPerson68List");
    return person68List;
  }

  @Factory("person68List") public void initPerson68List()
  { 
    log.info("initPerson68List");
    person68List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project58List") private List<ResearchProject> project58List;

  public List<ResearchProject> getProject58List()
  { 
    log.info("getProject58List");
    return project58List;
  }

  @Factory("project58List") public void initProject58List()
  { 
    log.info("initProject58List");
    project58List = em.createQuery("from " + "ResearchProject").getResultList();
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