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
    Person var35 = new Person();
    newAuthor2 = var35;
    initPerson159List();
    initResearchProject31List();
    initPublication22List();
    initPerson67List();
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

  public void removePerson2(Person person158)
  { 
    this.getTechnicalReport().getAuthors().remove(person158);
  }

  public void addPerson2(Person person158)
  { 
    this.getTechnicalReport().getAuthors().add(person158);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor2());
    Person var34 = new Person();
    newAuthor2 = var34;
  }

  public void removeResearchProject6(ResearchProject researchProject29)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject29);
  }

  public void addResearchProject6(ResearchProject researchProject29)
  { 
    this.getTechnicalReport().getProjects().add(researchProject29);
  }

  public void setPublication0(Publication publication23)
  { 
    technicalReport.setPreprintof(publication23);
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

  private String newPerson159;

  public void setNewPerson159(String p)
  { 
    newPerson159 = p;
  }

  public String getNewPerson159()
  { 
    return newPerson159;
  }

  public void selectPerson159(ValueChangeEvent event)
  { 
    log.info("selectPerson159" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person159 = em.find(Person.class, id);
      addPerson2(person159);
    }
  }

  @DataModel("person159List") private Map<String, String> person159List;

  public Map<String, String> getPerson159List()
  { 
    return person159List;
  }

  @Factory("person159List") public void initPerson159List()
  { 
    log.info("initPerson159List");
    person159List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person159List.put(p.getName(), p.getId().toString());
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
      addResearchProject6(researchProject31);
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

  private String newPublication22;

  public void setNewPublication22(String p)
  { 
    newPublication22 = p;
  }

  public String getNewPublication22()
  { 
    return newPublication22;
  }

  public void selectPublication22(ValueChangeEvent event)
  { 
    log.info("selectPublication22" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication22 = em.find(Publication.class, id);
      setPublication0(publication22);
    }
  }

  @DataModel("publication22List") private Map<String, String> publication22List;

  public Map<String, String> getPublication22List()
  { 
    return publication22List;
  }

  @Factory("publication22List") public void initPublication22List()
  { 
    log.info("initPublication22List");
    publication22List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication22List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person67List") private List<Person> person67List;

  public List<Person> getPerson67List()
  { 
    log.info("getPerson67List");
    return person67List;
  }

  @Factory("person67List") public void initPerson67List()
  { 
    log.info("initPerson67List");
    person67List = em.createQuery("from " + "Person").getResultList();
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