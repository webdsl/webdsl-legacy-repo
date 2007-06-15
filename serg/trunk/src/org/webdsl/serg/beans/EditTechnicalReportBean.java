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

@Stateful @Name("editTechnicalReport") public class EditTechnicalReportBean  implements EditTechnicalReportBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editTechnicalReport" + ".initalize()");
    if(technicalReportId == null)
    { 
      log.info("No " + "technicalReportId" + " defined, creating new " + "TechnicalReport");
      technicalReport = new TechnicalReport();
    }
    else
    { 
      technicalReport = em.find(TechnicalReport.class, technicalReportId);
    }
    Person var33 = new Person();
    newAuthor2 = var33;
    initPerson154List();
    initResearchProject20List();
    initPublication15List();
    initPerson62List();
    initProject57List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("technicalReport") private Long technicalReportId;

  private TechnicalReport technicalReport;

  public void setTechnicalReport(TechnicalReport technicalReport)
  { 
    log.info("setTechnicalReport");
    this.technicalReport = technicalReport;
  }

  public TechnicalReport getTechnicalReport()
  { 
    log.info("getTechnicalReport");
    return technicalReport;
  }

  public void removePerson2(Person person153)
  { 
    this.getTechnicalReport().getAuthors().remove(person153);
  }

  public void addPerson2(Person person153)
  { 
    this.getTechnicalReport().getAuthors().add(person153);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor2());
    Person var32 = new Person();
    newAuthor2 = var32;
  }

  public void removeResearchProject6(ResearchProject researchProject19)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject19);
  }

  public void addResearchProject6(ResearchProject researchProject19)
  { 
    this.getTechnicalReport().getProjects().add(researchProject19);
  }

  public void setPublication0(Publication publication16)
  { 
    technicalReport.setPreprintof(publication16);
  }

  @End public String cancel()
  { 
    return "/" + "viewTechnicalReport" + ".seam?" + ("technicalReport" + "=" + technicalReport.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getTechnicalReport());
    return "/" + "viewTechnicalReport" + ".seam?" + ("technicalReport" + "=" + technicalReport.getId() + "");
  }

  private String newPerson154;

  public void setNewPerson154(String p)
  { 
    newPerson154 = p;
  }

  public String getNewPerson154()
  { 
    return newPerson154;
  }

  public void selectPerson154(ValueChangeEvent event)
  { 
    log.info("selectPerson154" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person154 = em.find(Person.class, id);
      addPerson2(person154);
    }
  }

  @DataModel("person154List") private Map<String, String> person154List;

  public Map<String, String> getPerson154List()
  { 
    return person154List;
  }

  @Factory("person154List") public void initPerson154List()
  { 
    log.info("initPerson154List");
    person154List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person154List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject20;

  public void setNewResearchProject20(String p)
  { 
    newResearchProject20 = p;
  }

  public String getNewResearchProject20()
  { 
    return newResearchProject20;
  }

  public void selectResearchProject20(ValueChangeEvent event)
  { 
    log.info("selectResearchProject20" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject20 = em.find(ResearchProject.class, id);
      addResearchProject6(researchProject20);
    }
  }

  @DataModel("researchProject20List") private Map<String, String> researchProject20List;

  public Map<String, String> getResearchProject20List()
  { 
    return researchProject20List;
  }

  @Factory("researchProject20List") public void initResearchProject20List()
  { 
    log.info("initResearchProject20List");
    researchProject20List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject20List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication15;

  public void setNewPublication15(String p)
  { 
    newPublication15 = p;
  }

  public String getNewPublication15()
  { 
    return newPublication15;
  }

  public void selectPublication15(ValueChangeEvent event)
  { 
    log.info("selectPublication15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication15 = em.find(Publication.class, id);
      setPublication0(publication15);
    }
  }

  @DataModel("publication15List") private Map<String, String> publication15List;

  public Map<String, String> getPublication15List()
  { 
    return publication15List;
  }

  @Factory("publication15List") public void initPublication15List()
  { 
    log.info("initPublication15List");
    publication15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication15List.put(p.getName(), p.getId().toString());
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

  private Person newAuthor2;

  public Person getNewAuthor2()
  { 
    log.info("getNewAuthor2");
    return newAuthor2;
  }

  public void setNewAuthor2(Person newAuthor2)
  { 
    log.info("setNewAuthor2");
    this.newAuthor2 = newAuthor2;
  }
}