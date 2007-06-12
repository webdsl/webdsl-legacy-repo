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
    initPerson148List();
    initResearchProject29List();
    initPublication15List();
    initPerson60List();
    initProject55List();
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

  public void removePerson3(Person person147)
  { 
    this.getTechnicalReport().getAuthors().remove(person147);
  }

  public void addPerson3(Person person147)
  { 
    this.getTechnicalReport().getAuthors().add(person147);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor2());
    Person var30 = new Person();
    newAuthor2 = var30;
  }

  public void removeResearchProject9(ResearchProject researchProject28)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject28);
  }

  public void addResearchProject9(ResearchProject researchProject28)
  { 
    this.getTechnicalReport().getProjects().add(researchProject28);
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

  private String newPerson148;

  public void setNewPerson148(String p)
  { 
    newPerson148 = p;
  }

  public String getNewPerson148()
  { 
    return newPerson148;
  }

  public void selectPerson148(ValueChangeEvent event)
  { 
    log.info("selectPerson148" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person148 = em.find(Person.class, id);
      addPerson3(person148);
    }
  }

  @DataModel("person148List") private Map<String, String> person148List;

  public Map<String, String> getPerson148List()
  { 
    return person148List;
  }

  @Factory("person148List") public void initPerson148List()
  { 
    log.info("initPerson148List");
    person148List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person148List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject29;

  public void setNewResearchProject29(String p)
  { 
    newResearchProject29 = p;
  }

  public String getNewResearchProject29()
  { 
    return newResearchProject29;
  }

  public void selectResearchProject29(ValueChangeEvent event)
  { 
    log.info("selectResearchProject29" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject29 = em.find(ResearchProject.class, id);
      addResearchProject9(researchProject29);
    }
  }

  @DataModel("researchProject29List") private Map<String, String> researchProject29List;

  public Map<String, String> getResearchProject29List()
  { 
    return researchProject29List;
  }

  @Factory("researchProject29List") public void initResearchProject29List()
  { 
    log.info("initResearchProject29List");
    researchProject29List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject29List.put(p.getName(), p.getId().toString());
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

  @DataModel("person60List") private List<Person> person60List;

  public List<Person> getPerson60List()
  { 
    log.info("getPerson60List");
    return person60List;
  }

  @Factory("person60List") public void initPerson60List()
  { 
    log.info("initPerson60List");
    person60List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project55List") private List<ResearchProject> project55List;

  public List<ResearchProject> getProject55List()
  { 
    log.info("getProject55List");
    return project55List;
  }

  @Factory("project55List") public void initProject55List()
  { 
    log.info("initProject55List");
    project55List = em.createQuery("from " + "ResearchProject").getResultList();
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