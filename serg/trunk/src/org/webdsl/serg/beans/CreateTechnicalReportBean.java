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
    TechnicalReport var33 = new TechnicalReport();
    technicalReport = var33;
    Person var34 = new Person();
    newAuthor3 = var34;
    initPerson138List();
    initResearchProject22List();
    initPublication17List();
    initPerson62List();
    initProject57List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson3(Person person137)
  { 
    this.getTechnicalReport().getAuthors().remove(person137);
  }

  public void addPerson3(Person person137)
  { 
    this.getTechnicalReport().getAuthors().add(person137);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor3());
    Person var32 = new Person();
    newAuthor3 = var32;
  }

  public void removeResearchProject7(ResearchProject researchProject21)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject21);
  }

  public void addResearchProject7(ResearchProject researchProject21)
  { 
    this.getTechnicalReport().getProjects().add(researchProject21);
  }

  public void setPublication1(Publication publication18)
  { 
    technicalReport.setPreprintof(publication18);
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

  private String newPerson138;

  public void setNewPerson138(String p)
  { 
    newPerson138 = p;
  }

  public String getNewPerson138()
  { 
    return newPerson138;
  }

  public void selectPerson138(ValueChangeEvent event)
  { 
    log.info("selectPerson138" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person138 = em.find(Person.class, id);
      addPerson3(person138);
    }
  }

  @DataModel("person138List") private Map<String, String> person138List;

  public Map<String, String> getPerson138List()
  { 
    return person138List;
  }

  @Factory("person138List") public void initPerson138List()
  { 
    log.info("initPerson138List");
    person138List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person138List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject22;

  public void setNewResearchProject22(String p)
  { 
    newResearchProject22 = p;
  }

  public String getNewResearchProject22()
  { 
    return newResearchProject22;
  }

  public void selectResearchProject22(ValueChangeEvent event)
  { 
    log.info("selectResearchProject22" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject22 = em.find(ResearchProject.class, id);
      addResearchProject7(researchProject22);
    }
  }

  @DataModel("researchProject22List") private Map<String, String> researchProject22List;

  public Map<String, String> getResearchProject22List()
  { 
    return researchProject22List;
  }

  @Factory("researchProject22List") public void initResearchProject22List()
  { 
    log.info("initResearchProject22List");
    researchProject22List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject22List.put(p.getName(), p.getId().toString());
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
      setPublication1(publication17);
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

  @DataModel("person62List") private List<Person> person62List;

  public List<Person> getPerson62List()
  { 
    log.info("getPerson62List");
    return person62List;
  }

  @Factory("person62List") public void initPerson62List()
  { 
    log.info("initPerson62List");
    person62List = em.createQuery("from " + "Person").getResultList();
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