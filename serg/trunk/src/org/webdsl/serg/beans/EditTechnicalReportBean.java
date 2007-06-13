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
    Person var31 = new Person();
    newAuthor2 = var31;
    initPerson136List();
    initResearchProject20List();
    initPublication15List();
    initPerson61List();
    initProject56List();
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

  public void removePerson2(Person person135)
  { 
    this.getTechnicalReport().getAuthors().remove(person135);
  }

  public void addPerson2(Person person135)
  { 
    this.getTechnicalReport().getAuthors().add(person135);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor2());
    Person var30 = new Person();
    newAuthor2 = var30;
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

  private String newPerson136;

  public void setNewPerson136(String p)
  { 
    newPerson136 = p;
  }

  public String getNewPerson136()
  { 
    return newPerson136;
  }

  public void selectPerson136(ValueChangeEvent event)
  { 
    log.info("selectPerson136" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person136 = em.find(Person.class, id);
      addPerson2(person136);
    }
  }

  @DataModel("person136List") private Map<String, String> person136List;

  public Map<String, String> getPerson136List()
  { 
    return person136List;
  }

  @Factory("person136List") public void initPerson136List()
  { 
    log.info("initPerson136List");
    person136List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person136List.put(p.getName(), p.getId().toString());
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