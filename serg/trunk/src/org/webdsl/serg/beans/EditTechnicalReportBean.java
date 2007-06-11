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
    initPerson50List();
    initResearchProject27List();
    initPublication7List();
    initPerson1037List();
    initProject1137List();
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

  public void removePerson3(Person person49)
  { 
    this.getTechnicalReport().getAuthors().remove(person49);
  }

  public void addPerson3(Person person49)
  { 
    this.getTechnicalReport().getAuthors().add(person49);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor2());
    Person var30 = new Person();
    newAuthor2 = var30;
  }

  public void removeResearchProject9(ResearchProject researchProject26)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject26);
  }

  public void addResearchProject9(ResearchProject researchProject26)
  { 
    this.getTechnicalReport().getProjects().add(researchProject26);
  }

  public void setPublication0(Publication publication8)
  { 
    technicalReport.setPreprintof(publication8);
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

  private String newPerson50;

  public void setNewPerson50(String p)
  { 
    newPerson50 = p;
  }

  public String getNewPerson50()
  { 
    return newPerson50;
  }

  public void selectPerson50(ValueChangeEvent event)
  { 
    log.info("selectPerson50" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person50 = em.find(Person.class, id);
      addPerson3(person50);
    }
  }

  @DataModel("person50List") private Map<String, String> person50List;

  public Map<String, String> getPerson50List()
  { 
    return person50List;
  }

  @Factory("person50List") public void initPerson50List()
  { 
    log.info("initPerson50List");
    person50List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person50List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject27;

  public void setNewResearchProject27(String p)
  { 
    newResearchProject27 = p;
  }

  public String getNewResearchProject27()
  { 
    return newResearchProject27;
  }

  public void selectResearchProject27(ValueChangeEvent event)
  { 
    log.info("selectResearchProject27" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject27 = em.find(ResearchProject.class, id);
      addResearchProject9(researchProject27);
    }
  }

  @DataModel("researchProject27List") private Map<String, String> researchProject27List;

  public Map<String, String> getResearchProject27List()
  { 
    return researchProject27List;
  }

  @Factory("researchProject27List") public void initResearchProject27List()
  { 
    log.info("initResearchProject27List");
    researchProject27List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject27List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication7;

  public void setNewPublication7(String p)
  { 
    newPublication7 = p;
  }

  public String getNewPublication7()
  { 
    return newPublication7;
  }

  public void selectPublication7(ValueChangeEvent event)
  { 
    log.info("selectPublication7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication7 = em.find(Publication.class, id);
      setPublication0(publication7);
    }
  }

  @DataModel("publication7List") private Map<String, String> publication7List;

  public Map<String, String> getPublication7List()
  { 
    return publication7List;
  }

  @Factory("publication7List") public void initPublication7List()
  { 
    log.info("initPublication7List");
    publication7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1037List") private List<Person> person1037List;

  public List<Person> getPerson1037List()
  { 
    log.info("getPerson1037List");
    return person1037List;
  }

  @Factory("person1037List") public void initPerson1037List()
  { 
    log.info("initPerson1037List");
    person1037List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1137List") private List<ResearchProject> project1137List;

  public List<ResearchProject> getProject1137List()
  { 
    log.info("getProject1137List");
    return project1137List;
  }

  @Factory("project1137List") public void initProject1137List()
  { 
    log.info("initProject1137List");
    project1137List = em.createQuery("from " + "ResearchProject").getResultList();
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