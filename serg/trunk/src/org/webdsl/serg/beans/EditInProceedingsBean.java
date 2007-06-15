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
    Person var38 = new Person();
    newAuthor4 = var38;
    initPerson159List();
    initResearchProject25List();
    initConference5List();
    initPerson66List();
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

  public void removePerson4(Person person158)
  { 
    this.getInProceedings().getAuthors().remove(person158);
  }

  public void addPerson4(Person person158)
  { 
    this.getInProceedings().getAuthors().add(person158);
  }

  public void addNewAuthor()
  { 
    this.getInProceedings().getAuthors().add(this.getNewAuthor4());
    Person var37 = new Person();
    newAuthor4 = var37;
  }

  public void removeResearchProject8(ResearchProject researchProject24)
  { 
    this.getInProceedings().getProjects().remove(researchProject24);
  }

  public void addResearchProject8(ResearchProject researchProject24)
  { 
    this.getInProceedings().getProjects().add(researchProject24);
  }

  public void setConference0(Conference conference6)
  { 
    inProceedings.setConference(conference6);
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
      addPerson4(person159);
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
      addResearchProject8(researchProject25);
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

  private String newConference5;

  public void setNewConference5(String p)
  { 
    newConference5 = p;
  }

  public String getNewConference5()
  { 
    return newConference5;
  }

  public void selectConference5(ValueChangeEvent event)
  { 
    log.info("selectConference5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Conference conference5 = em.find(Conference.class, id);
      setConference0(conference5);
    }
  }

  @DataModel("conference5List") private Map<String, String> conference5List;

  public Map<String, String> getConference5List()
  { 
    return conference5List;
  }

  @Factory("conference5List") public void initConference5List()
  { 
    log.info("initConference5List");
    conference5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Conference").getResultList())
    { 
      Conference p = (Conference)o;
      conference5List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person66List") private List<Person> person66List;

  public List<Person> getPerson66List()
  { 
    log.info("getPerson66List");
    return person66List;
  }

  @Factory("person66List") public void initPerson66List()
  { 
    log.info("initPerson66List");
    person66List = em.createQuery("from " + "Person").getResultList();
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