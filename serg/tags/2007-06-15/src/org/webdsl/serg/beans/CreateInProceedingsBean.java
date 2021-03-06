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
    InProceedings var44 = new InProceedings();
    inProceedings = var44;
    Person var45 = new Person();
    newAuthor5 = var45;
    initPerson166List();
    initResearchProject38List();
    initConference8List();
    initPerson72List();
    initProject62List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson5(Person person165)
  { 
    this.getInProceedings().getAuthors().remove(person165);
  }

  public void addPerson5(Person person165)
  { 
    this.getInProceedings().getAuthors().add(person165);
  }

  public void addNewAuthor()
  { 
    this.getInProceedings().getAuthors().add(this.getNewAuthor5());
    Person var43 = new Person();
    newAuthor5 = var43;
  }

  public void removeResearchProject9(ResearchProject researchProject37)
  { 
    this.getInProceedings().getProjects().remove(researchProject37);
  }

  public void addResearchProject9(ResearchProject researchProject37)
  { 
    this.getInProceedings().getProjects().add(researchProject37);
  }

  public void setConference1(Conference conference9)
  { 
    inProceedings.setConference(conference9);
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

  private String newPerson166;

  public void setNewPerson166(String p)
  { 
    newPerson166 = p;
  }

  public String getNewPerson166()
  { 
    return newPerson166;
  }

  public void selectPerson166(ValueChangeEvent event)
  { 
    log.info("selectPerson166" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person166 = em.find(Person.class, id);
      addPerson5(person166);
    }
  }

  @DataModel("person166List") private Map<String, String> person166List;

  public Map<String, String> getPerson166List()
  { 
    return person166List;
  }

  @Factory("person166List") public void initPerson166List()
  { 
    log.info("initPerson166List");
    person166List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person166List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject38;

  public void setNewResearchProject38(String p)
  { 
    newResearchProject38 = p;
  }

  public String getNewResearchProject38()
  { 
    return newResearchProject38;
  }

  public void selectResearchProject38(ValueChangeEvent event)
  { 
    log.info("selectResearchProject38" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject38 = em.find(ResearchProject.class, id);
      addResearchProject9(researchProject38);
    }
  }

  @DataModel("researchProject38List") private Map<String, String> researchProject38List;

  public Map<String, String> getResearchProject38List()
  { 
    return researchProject38List;
  }

  @Factory("researchProject38List") public void initResearchProject38List()
  { 
    log.info("initResearchProject38List");
    researchProject38List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject38List.put(p.getName(), p.getId().toString());
    }
  }

  private String newConference8;

  public void setNewConference8(String p)
  { 
    newConference8 = p;
  }

  public String getNewConference8()
  { 
    return newConference8;
  }

  public void selectConference8(ValueChangeEvent event)
  { 
    log.info("selectConference8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Conference conference8 = em.find(Conference.class, id);
      setConference1(conference8);
    }
  }

  @DataModel("conference8List") private Map<String, String> conference8List;

  public Map<String, String> getConference8List()
  { 
    return conference8List;
  }

  @Factory("conference8List") public void initConference8List()
  { 
    log.info("initConference8List");
    conference8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Conference").getResultList())
    { 
      Conference p = (Conference)o;
      conference8List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person72List") private List<Person> person72List;

  public List<Person> getPerson72List()
  { 
    log.info("getPerson72List");
    return person72List;
  }

  @Factory("person72List") public void initPerson72List()
  { 
    log.info("initPerson72List");
    person72List = em.createQuery("from " + "Person").getResultList();
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