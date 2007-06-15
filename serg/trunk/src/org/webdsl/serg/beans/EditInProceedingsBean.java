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
    Person var42 = new Person();
    newAuthor4 = var42;
    initPerson164List();
    initResearchProject36List();
    initConference6List();
    initPerson71List();
    initProject61List();
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

  public void removePerson4(Person person163)
  { 
    this.getInProceedings().getAuthors().remove(person163);
  }

  public void addPerson4(Person person163)
  { 
    this.getInProceedings().getAuthors().add(person163);
  }

  public void addNewAuthor()
  { 
    this.getInProceedings().getAuthors().add(this.getNewAuthor4());
    Person var41 = new Person();
    newAuthor4 = var41;
  }

  public void removeResearchProject8(ResearchProject researchProject35)
  { 
    this.getInProceedings().getProjects().remove(researchProject35);
  }

  public void addResearchProject8(ResearchProject researchProject35)
  { 
    this.getInProceedings().getProjects().add(researchProject35);
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

  private String newPerson164;

  public void setNewPerson164(String p)
  { 
    newPerson164 = p;
  }

  public String getNewPerson164()
  { 
    return newPerson164;
  }

  public void selectPerson164(ValueChangeEvent event)
  { 
    log.info("selectPerson164" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person164 = em.find(Person.class, id);
      addPerson4(person164);
    }
  }

  @DataModel("person164List") private Map<String, String> person164List;

  public Map<String, String> getPerson164List()
  { 
    return person164List;
  }

  @Factory("person164List") public void initPerson164List()
  { 
    log.info("initPerson164List");
    person164List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person164List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject36;

  public void setNewResearchProject36(String p)
  { 
    newResearchProject36 = p;
  }

  public String getNewResearchProject36()
  { 
    return newResearchProject36;
  }

  public void selectResearchProject36(ValueChangeEvent event)
  { 
    log.info("selectResearchProject36" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject36 = em.find(ResearchProject.class, id);
      addResearchProject8(researchProject36);
    }
  }

  @DataModel("researchProject36List") private Map<String, String> researchProject36List;

  public Map<String, String> getResearchProject36List()
  { 
    return researchProject36List;
  }

  @Factory("researchProject36List") public void initResearchProject36List()
  { 
    log.info("initResearchProject36List");
    researchProject36List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject36List.put(p.getName(), p.getId().toString());
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Conference conference6 = em.find(Conference.class, id);
      setConference0(conference6);
    }
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

  @DataModel("person71List") private List<Person> person71List;

  public List<Person> getPerson71List()
  { 
    log.info("getPerson71List");
    return person71List;
  }

  @Factory("person71List") public void initPerson71List()
  { 
    log.info("initPerson71List");
    person71List = em.createQuery("from " + "Person").getResultList();
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