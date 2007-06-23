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

@Stateful @Name("editInProceedings") public class EditInProceedingsBean  implements EditInProceedingsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editInProceedings" + ".initalize()");
    if(inProceedingsId == null)
    { 
      log.info("No " + "inProceedingsId" + " defined, creating new " + "InProceedings");
      inProceedings = new InProceedings();
    }
    else
    { 
      inProceedings = em.find(InProceedings.class, inProceedingsId);
    }
    Person var49 = new Person();
    newAuthor4 = var49;
    initPerson185List();
    initResearchProject43List();
    initConference6List();
    initPerson76List();
    initProject65List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("inProceedings") private Long inProceedingsId;

  private InProceedings inProceedings;

  public void setInProceedings(InProceedings inProceedings)
  { 
    log.info("setInProceedings");
    this.inProceedings = inProceedings;
  }

  public InProceedings getInProceedings()
  { 
    log.info("getInProceedings");
    return inProceedings;
  }

  public void removePerson6(Person person184)
  { 
    this.getInProceedings().getAuthors().remove(person184);
  }

  public void addPerson6(Person person184)
  { 
    this.getInProceedings().getAuthors().add(person184);
  }

  public void addNewAuthor()
  { 
    this.getInProceedings().getAuthors().add(this.getNewAuthor4());
    Person var48 = new Person();
    newAuthor4 = var48;
  }

  public void removeResearchProject10(ResearchProject researchProject42)
  { 
    this.getInProceedings().getProjects().remove(researchProject42);
  }

  public void addResearchProject10(ResearchProject researchProject42)
  { 
    this.getInProceedings().getProjects().add(researchProject42);
  }

  public void setConference0(Conference conference7)
  { 
    inProceedings.setConference(conference7);
  }

  @End public String cancel()
  { 
    return "/" + "viewInProceedings" + ".seam?" + ("inProceedings" + "=" + inProceedings.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getInProceedings());
    return "/" + "viewInProceedings" + ".seam?" + ("inProceedings" + "=" + inProceedings.getId() + "");
  }

  private String newPerson185;

  public void setNewPerson185(String p)
  { 
    newPerson185 = p;
  }

  public String getNewPerson185()
  { 
    return newPerson185;
  }

  public void selectPerson185(ValueChangeEvent event)
  { 
    log.info("selectPerson185" + ": new value = " + " " + event.getNewValue());
    Person person185 = (Person)event.getNewValue();
  }

  @DataModel("person185List") private Map<String, String> person185List;

  public Map<String, String> getPerson185List()
  { 
    return person185List;
  }

  @Factory("person185List") public void initPerson185List()
  { 
    log.info("initPerson185List");
    person185List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person185List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject43;

  public void setNewResearchProject43(String p)
  { 
    newResearchProject43 = p;
  }

  public String getNewResearchProject43()
  { 
    return newResearchProject43;
  }

  public void selectResearchProject43(ValueChangeEvent event)
  { 
    log.info("selectResearchProject43" + ": new value = " + " " + event.getNewValue());
    ResearchProject researchProject43 = (ResearchProject)event.getNewValue();
  }

  @DataModel("researchProject43List") private Map<String, String> researchProject43List;

  public Map<String, String> getResearchProject43List()
  { 
    return researchProject43List;
  }

  @Factory("researchProject43List") public void initResearchProject43List()
  { 
    log.info("initResearchProject43List");
    researchProject43List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject43List.put(p.getName(), p.getId().toString());
    }
  }

  private String newConference6;

  public void setNewConference6(String p)
  { 
    newConference6 = p;
  }

  public String getNewConference6()
  { 
    return newConference6;
  }

  public void selectConference6(ValueChangeEvent event)
  { 
    log.info("selectConference6" + ": new value = " + " " + event.getNewValue());
    Conference conference6 = (Conference)event.getNewValue();
  }

  @DataModel("conference6List") private Map<String, String> conference6List;

  public Map<String, String> getConference6List()
  { 
    return conference6List;
  }

  @Factory("conference6List") public void initConference6List()
  { 
    log.info("initConference6List");
    conference6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Conference").getResultList())
    { 
      Conference p = (Conference)o;
      conference6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person76List") private List<Person> person76List;

  public List<Person> getPerson76List()
  { 
    log.info("getPerson76List");
    return person76List;
  }

  @Factory("person76List") public void initPerson76List()
  { 
    log.info("initPerson76List");
    person76List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project65List") private List<ResearchProject> project65List;

  public List<ResearchProject> getProject65List()
  { 
    log.info("getProject65List");
    return project65List;
  }

  @Factory("project65List") public void initProject65List()
  { 
    log.info("initProject65List");
    project65List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Person newAuthor4;

  public Person getNewAuthor4()
  { 
    log.info("getNewAuthor4");
    return newAuthor4;
  }

  public void setNewAuthor4(Person newAuthor4)
  { 
    log.info("setNewAuthor4");
    this.newAuthor4 = newAuthor4;
  }
}