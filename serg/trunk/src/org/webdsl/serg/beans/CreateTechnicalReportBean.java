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
    initPerson150List();
    initResearchProject31List();
    initPublication17List();
    initPerson61List();
    initProject56List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson4(Person person149)
  { 
    this.getTechnicalReport().getAuthors().remove(person149);
  }

  public void addPerson4(Person person149)
  { 
    this.getTechnicalReport().getAuthors().add(person149);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor3());
    Person var32 = new Person();
    newAuthor3 = var32;
  }

  public void removeResearchProject10(ResearchProject researchProject30)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject30);
  }

  public void addResearchProject10(ResearchProject researchProject30)
  { 
    this.getTechnicalReport().getProjects().add(researchProject30);
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

  private String newPerson150;

  public void setNewPerson150(String p)
  { 
    newPerson150 = p;
  }

  public String getNewPerson150()
  { 
    return newPerson150;
  }

  public void selectPerson150(ValueChangeEvent event)
  { 
    log.info("selectPerson150" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person150 = em.find(Person.class, id);
      addPerson4(person150);
    }
  }

  @DataModel("person150List") private Map<String, String> person150List;

  public Map<String, String> getPerson150List()
  { 
    return person150List;
  }

  @Factory("person150List") public void initPerson150List()
  { 
    log.info("initPerson150List");
    person150List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person150List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject31;

  public void setNewResearchProject31(String p)
  { 
    newResearchProject31 = p;
  }

  public String getNewResearchProject31()
  { 
    return newResearchProject31;
  }

  public void selectResearchProject31(ValueChangeEvent event)
  { 
    log.info("selectResearchProject31" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject31 = em.find(ResearchProject.class, id);
      addResearchProject10(researchProject31);
    }
  }

  @DataModel("researchProject31List") private Map<String, String> researchProject31List;

  public Map<String, String> getResearchProject31List()
  { 
    return researchProject31List;
  }

  @Factory("researchProject31List") public void initResearchProject31List()
  { 
    log.info("initResearchProject31List");
    researchProject31List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject31List.put(p.getName(), p.getId().toString());
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

  @DataModel("person61List") private List<Person> person61List;

  public List<Person> getPerson61List()
  { 
    log.info("getPerson61List");
    return person61List;
  }

  @Factory("person61List") public void initPerson61List()
  { 
    log.info("initPerson61List");
    person61List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project56List") private List<ResearchProject> project56List;

  public List<ResearchProject> getProject56List()
  { 
    log.info("getProject56List");
    return project56List;
  }

  @Factory("project56List") public void initProject56List()
  { 
    log.info("initProject56List");
    project56List = em.createQuery("from " + "ResearchProject").getResultList();
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