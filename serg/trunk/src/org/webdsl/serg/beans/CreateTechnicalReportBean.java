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
    initPerson52List();
    initResearchProject29List();
    initPublication9List();
    initPerson1038List();
    initProject1138List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson4(Person person51)
  { 
    this.getTechnicalReport().getAuthors().remove(person51);
  }

  public void addPerson4(Person person51)
  { 
    this.getTechnicalReport().getAuthors().add(person51);
  }

  public void addNewAuthor()
  { 
    this.getTechnicalReport().getAuthors().add(this.getNewAuthor3());
    Person var32 = new Person();
    newAuthor3 = var32;
  }

  public void removeResearchProject10(ResearchProject researchProject28)
  { 
    this.getTechnicalReport().getProjects().remove(researchProject28);
  }

  public void addResearchProject10(ResearchProject researchProject28)
  { 
    this.getTechnicalReport().getProjects().add(researchProject28);
  }

  public void setPublication1(Publication publication10)
  { 
    technicalReport.setPreprintof(publication10);
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

  private String newPerson52;

  public void setNewPerson52(String p)
  { 
    newPerson52 = p;
  }

  public String getNewPerson52()
  { 
    return newPerson52;
  }

  public void selectPerson52(ValueChangeEvent event)
  { 
    log.info("selectPerson52" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person52 = em.find(Person.class, id);
      addPerson4(person52);
    }
  }

  @DataModel("person52List") private Map<String, String> person52List;

  public Map<String, String> getPerson52List()
  { 
    return person52List;
  }

  @Factory("person52List") public void initPerson52List()
  { 
    log.info("initPerson52List");
    person52List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person52List.put(p.getName(), p.getId().toString());
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
      addResearchProject10(researchProject29);
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

  private String newPublication9;

  public void setNewPublication9(String p)
  { 
    newPublication9 = p;
  }

  public String getNewPublication9()
  { 
    return newPublication9;
  }

  public void selectPublication9(ValueChangeEvent event)
  { 
    log.info("selectPublication9" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Publication publication9 = em.find(Publication.class, id);
      setPublication1(publication9);
    }
  }

  @DataModel("publication9List") private Map<String, String> publication9List;

  public Map<String, String> getPublication9List()
  { 
    return publication9List;
  }

  @Factory("publication9List") public void initPublication9List()
  { 
    log.info("initPublication9List");
    publication9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication9List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1038List") private List<Person> person1038List;

  public List<Person> getPerson1038List()
  { 
    log.info("getPerson1038List");
    return person1038List;
  }

  @Factory("person1038List") public void initPerson1038List()
  { 
    log.info("initPerson1038List");
    person1038List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1138List") private List<ResearchProject> project1138List;

  public List<ResearchProject> getProject1138List()
  { 
    log.info("getProject1138List");
    return project1138List;
  }

  @Factory("project1138List") public void initProject1138List()
  { 
    log.info("initProject1138List");
    project1138List = em.createQuery("from " + "ResearchProject").getResultList();
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