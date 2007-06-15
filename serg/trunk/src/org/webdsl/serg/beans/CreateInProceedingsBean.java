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

@Stateful @Name("createInProceedings") public class CreateInProceedingsBean  implements CreateInProceedingsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createInProceedings" + ".initalize()");
    InProceedings var40 = new InProceedings();
    inProceedings = var40;
    Person var41 = new Person();
    newAuthor5 = var41;
    initPerson161List();
    initResearchProject27List();
    initConference7List();
    initPerson67List();
    initProject62List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson5(Person person160)
  { 
    this.getInProceedings().getAuthors().remove(person160);
  }

  public void addPerson5(Person person160)
  { 
    this.getInProceedings().getAuthors().add(person160);
  }

  public void addNewAuthor()
  { 
    this.getInProceedings().getAuthors().add(this.getNewAuthor5());
    Person var39 = new Person();
    newAuthor5 = var39;
  }

  public void removeResearchProject9(ResearchProject researchProject26)
  { 
    this.getInProceedings().getProjects().remove(researchProject26);
  }

  public void addResearchProject9(ResearchProject researchProject26)
  { 
    this.getInProceedings().getProjects().add(researchProject26);
  }

  public void setConference1(Conference conference8)
  { 
    inProceedings.setConference(conference8);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getInProceedings());
    return "/" + "viewInProceedings" + ".seam?" + ("inProceedings" + "=" + inProceedings.getId() + "");
  }

  private String newPerson161;

  public void setNewPerson161(String p)
  { 
    newPerson161 = p;
  }

  public String getNewPerson161()
  { 
    return newPerson161;
  }

  public void selectPerson161(ValueChangeEvent event)
  { 
    log.info("selectPerson161" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person161 = em.find(Person.class, id);
      addPerson5(person161);
    }
  }

  @DataModel("person161List") private Map<String, String> person161List;

  public Map<String, String> getPerson161List()
  { 
    return person161List;
  }

  @Factory("person161List") public void initPerson161List()
  { 
    log.info("initPerson161List");
    person161List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person161List.put(p.getName(), p.getId().toString());
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

  private String newConference7;

  public void setNewConference7(String p)
  { 
    newConference7 = p;
  }

  public String getNewConference7()
  { 
    return newConference7;
  }

  public void selectConference7(ValueChangeEvent event)
  { 
    log.info("selectConference7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Conference conference7 = em.find(Conference.class, id);
      setConference1(conference7);
    }
  }

  @DataModel("conference7List") private Map<String, String> conference7List;

  public Map<String, String> getConference7List()
  { 
    return conference7List;
  }

  @Factory("conference7List") public void initConference7List()
  { 
    log.info("initConference7List");
    conference7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Conference").getResultList())
    { 
      Conference p = (Conference)o;
      conference7List.put(p.getName(), p.getId().toString());
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

  @DataModel("project62List") private List<ResearchProject> project62List;

  public List<ResearchProject> getProject62List()
  { 
    log.info("getProject62List");
    return project62List;
  }

  @Factory("project62List") public void initProject62List()
  { 
    log.info("initProject62List");
    project62List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private InProceedings inProceedings;

  public InProceedings getInProceedings()
  { 
    log.info("getInProceedings");
    return inProceedings;
  }

  public void setInProceedings(InProceedings inProceedings)
  { 
    log.info("setInProceedings");
    this.inProceedings = inProceedings;
  }

  private Person newAuthor5;

  public Person getNewAuthor5()
  { 
    log.info("getNewAuthor5");
    return newAuthor5;
  }

  public void setNewAuthor5(Person newAuthor5)
  { 
    log.info("setNewAuthor5");
    this.newAuthor5 = newAuthor5;
  }
}