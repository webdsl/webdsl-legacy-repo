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
    TechnicalReport var44 = new TechnicalReport();
    technicalReport = var44;
    Person var45 = new Person();
    newAuthor3 = var45;
    initPerson182List();
    initResearchProject39List();
    initPublication24List();
    initPerson73List();
    initProject62List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson5(Person person181)
  { 
    this.getTechnicalReport().getAuthors().remove(person181);
  }

  public void addPerson5(Person person181)
  { 
    this.getTechnicalReport().getAuthors().add(person181);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor3());
    Person var43 = new Person();
    newAuthor3 = var43;
  }

  public void removeResearchProject9(ResearchProject researchProject38)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject38);
  }

  public void addResearchProject9(ResearchProject researchProject38)
  { 
    this.getTechnicalReport().getProjects().add(researchProject38);
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

  private String newPerson182;

  public void setNewPerson182(String p)
  { 
    newPerson182 = p;
  }

  public String getNewPerson182()
  { 
    return newPerson182;
  }

  public void selectPerson182(ValueChangeEvent event)
  { 
    log.info("selectPerson182" + ": new value = " + " " + event.getNewValue());
    Person person182 = (Person)event.getNewValue();
  }

  @DataModel("person182List") private Map<String, String> person182List;

  public Map<String, String> getPerson182List()
  { 
    return person182List;
  }

  @Factory("person182List") public void initPerson182List()
  { 
    log.info("initPerson182List");
    person182List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person182List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject39;

  public void setNewResearchProject39(String p)
  { 
    newResearchProject39 = p;
  }

  public String getNewResearchProject39()
  { 
    return newResearchProject39;
  }

  public void selectResearchProject39(ValueChangeEvent event)
  { 
    log.info("selectResearchProject39" + ": new value = " + " " + event.getNewValue());
    ResearchProject researchProject39 = (ResearchProject)event.getNewValue();
  }

  @DataModel("researchProject39List") private Map<String, String> researchProject39List;

  public Map<String, String> getResearchProject39List()
  { 
    return researchProject39List;
  }

  @Factory("researchProject39List") public void initResearchProject39List()
  { 
    log.info("initResearchProject39List");
    researchProject39List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject39List.put(p.getName(), p.getId().toString());
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
    Publication publication24 = (Publication)event.getNewValue();
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

  @DataModel("person73List") private List<Person> person73List;

  public List<Person> getPerson73List()
  { 
    log.info("getPerson73List");
    return person73List;
  }

  @Factory("person73List") public void initPerson73List()
  { 
    log.info("initPerson73List");
    person73List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project62List") private List<ResearchProject> project62List;

  public List<ResearchProject> getProject62List()
  { 
    log.info("getProject62List");
    return project62List;
  }

  @Factory("project62List") public void initProject62List()
  { 
    log.info("initProject62List");
    project62List = em.createQuery("from " + "ResearchProject").getResultList();
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