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
    Person var42 = new Person();
    newAuthor2 = var42;
    initPerson194List();
    initResearchProject37List();
    initPublication22List();
    initPerson74List();
    initProject61List();
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

  public void removePerson4(Person person193)
  { 
    this.getTechnicalReport().getAuthors().remove(person193);
  }

  public void addPerson4(Person person193)
  { 
    this.getTechnicalReport().getAuthors().add(person193);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor2());
    Person var41 = new Person();
    newAuthor2 = var41;
  }

  public void removeResearchProject8(ResearchProject researchProject36)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject36);
  }

  public void addResearchProject8(ResearchProject researchProject36)
  { 
    this.getTechnicalReport().getProjects().add(researchProject36);
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

  private String newPerson194;

  public void setNewPerson194(String p)
  { 
    newPerson194 = p;
  }

  public String getNewPerson194()
  { 
    return newPerson194;
  }

  public void selectPerson194(ValueChangeEvent event)
  { 
    log.info("selectPerson194" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person194 = em.find(Person.class, id);
      addPerson4(person194);
    }
  }

  @DataModel("person194List") private Map<String, String> person194List;

  public Map<String, String> getPerson194List()
  { 
    return person194List;
  }

  @Factory("person194List") public void initPerson194List()
  { 
    log.info("initPerson194List");
    person194List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person194List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject37;

  public void setNewResearchProject37(String p)
  { 
    newResearchProject37 = p;
  }

  public String getNewResearchProject37()
  { 
    return newResearchProject37;
  }

  public void selectResearchProject37(ValueChangeEvent event)
  { 
    log.info("selectResearchProject37" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject37 = em.find(ResearchProject.class, id);
      addResearchProject8(researchProject37);
    }
  }

  @DataModel("researchProject37List") private Map<String, String> researchProject37List;

  public Map<String, String> getResearchProject37List()
  { 
    return researchProject37List;
  }

  @Factory("researchProject37List") public void initResearchProject37List()
  { 
    log.info("initResearchProject37List");
    researchProject37List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject37List.put(p.getName(), p.getId().toString());
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

  @DataModel("person74List") private List<Person> person74List;

  public List<Person> getPerson74List()
  { 
    log.info("getPerson74List");
    return person74List;
  }

  @Factory("person74List") public void initPerson74List()
  { 
    log.info("initPerson74List");
    person74List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project61List") private List<ResearchProject> project61List;

  public List<ResearchProject> getProject61List()
  { 
    log.info("getProject61List");
    return project61List;
  }

  @Factory("project61List") public void initProject61List()
  { 
    log.info("initProject61List");
    project61List = em.createQuery("from " + "ResearchProject").getResultList();
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