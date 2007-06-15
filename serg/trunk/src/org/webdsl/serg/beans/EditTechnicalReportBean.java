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
    Person var34 = new Person();
    newAuthor2 = var34;
    initPerson158List();
    initResearchProject25List();
    initPublication16List();
    initPerson62List();
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

  public void removePerson2(Person person157)
  { 
    this.getTechnicalReport().getAuthors().remove(person157);
  }

  public void addPerson2(Person person157)
  { 
    this.getTechnicalReport().getAuthors().add(person157);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor2());
    Person var33 = new Person();
    newAuthor2 = var33;
  }

  public void removeResearchProject6(ResearchProject researchProject24)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject24);
  }

  public void addResearchProject6(ResearchProject researchProject24)
  { 
    this.getTechnicalReport().getProjects().add(researchProject24);
  }

  public void setPublication0(Publication publication17)
  { 
    technicalReport.setPreprintof(publication17);
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

  private String newPerson158;

  public void setNewPerson158(String p)
  { 
    newPerson158 = p;
  }

  public String getNewPerson158()
  { 
    return newPerson158;
  }

  public void selectPerson158(ValueChangeEvent event)
  { 
    log.info("selectPerson158" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person158 = em.find(Person.class, id);
      addPerson2(person158);
    }
  }

  @DataModel("person158List") private Map<String, String> person158List;

  public Map<String, String> getPerson158List()
  { 
    return person158List;
  }

  @Factory("person158List") public void initPerson158List()
  { 
    log.info("initPerson158List");
    person158List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person158List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject25;

  public void setNewResearchProject25(String p)
  { 
    newResearchProject25 = p;
  }

  public String getNewResearchProject25()
  { 
    return newResearchProject25;
  }

  public void selectResearchProject25(ValueChangeEvent event)
  { 
    log.info("selectResearchProject25" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject25 = em.find(ResearchProject.class, id);
      addResearchProject6(researchProject25);
    }
  }

  @DataModel("researchProject25List") private Map<String, String> researchProject25List;

  public Map<String, String> getResearchProject25List()
  { 
    return researchProject25List;
  }

  @Factory("researchProject25List") public void initResearchProject25List()
  { 
    log.info("initResearchProject25List");
    researchProject25List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject25List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication16;

  public void setNewPublication16(String p)
  { 
    newPublication16 = p;
  }

  public String getNewPublication16()
  { 
    return newPublication16;
  }

  public void selectPublication16(ValueChangeEvent event)
  { 
    log.info("selectPublication16" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication16 = em.find(Publication.class, id);
      setPublication0(publication16);
    }
  }

  @DataModel("publication16List") private Map<String, String> publication16List;

  public Map<String, String> getPublication16List()
  { 
    return publication16List;
  }

  @Factory("publication16List") public void initPublication16List()
  { 
    log.info("initPublication16List");
    publication16List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication16List.put(p.getName(), p.getId().toString());
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