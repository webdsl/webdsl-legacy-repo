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
    InProceedings var51 = new InProceedings();
    inProceedings = var51;
    Person var52 = new Person();
    newAuthor5 = var52;
    initPerson187List();
    initResearchProject45List();
    initConference8List();
    initPerson77List();
    initProject66List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson7(Person person186)
  { 
    this.getInProceedings().getAuthors().remove(person186);
  }

  public void addPerson7(Person person186)
  { 
    this.getInProceedings().getAuthors().add(person186);
  }

  public void addNewAuthor()
  { 
    this.getInProceedings().getAuthors().add(this.getNewAuthor5());
    Person var50 = new Person();
    newAuthor5 = var50;
  }

  public void removeResearchProject11(ResearchProject researchProject44)
  { 
    this.getInProceedings().getProjects().remove(researchProject44);
  }

  public void addResearchProject11(ResearchProject researchProject44)
  { 
    this.getInProceedings().getProjects().add(researchProject44);
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

  private String newPerson187;

  public void setNewPerson187(String p)
  { 
    newPerson187 = p;
  }

  public String getNewPerson187()
  { 
    return newPerson187;
  }

  public void selectPerson187(ValueChangeEvent event)
  { 
    log.info("selectPerson187" + ": new value = " + " " + event.getNewValue());
    Person person187 = (Person)event.getNewValue();
  }

  @DataModel("person187List") private Map<String, String> person187List;

  public Map<String, String> getPerson187List()
  { 
    return person187List;
  }

  @Factory("person187List") public void initPerson187List()
  { 
    log.info("initPerson187List");
    person187List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person187List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject45;

  public void setNewResearchProject45(String p)
  { 
    newResearchProject45 = p;
  }

  public String getNewResearchProject45()
  { 
    return newResearchProject45;
  }

  public void selectResearchProject45(ValueChangeEvent event)
  { 
    log.info("selectResearchProject45" + ": new value = " + " " + event.getNewValue());
    ResearchProject researchProject45 = (ResearchProject)event.getNewValue();
  }

  @DataModel("researchProject45List") private Map<String, String> researchProject45List;

  public Map<String, String> getResearchProject45List()
  { 
    return researchProject45List;
  }

  @Factory("researchProject45List") public void initResearchProject45List()
  { 
    log.info("initResearchProject45List");
    researchProject45List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject45List.put(p.getName(), p.getId().toString());
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
    Conference conference8 = (Conference)event.getNewValue();
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

  @DataModel("person77List") private List<Person> person77List;

  public List<Person> getPerson77List()
  { 
    log.info("getPerson77List");
    return person77List;
  }

  @Factory("person77List") public void initPerson77List()
  { 
    log.info("initPerson77List");
    person77List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project66List") private List<ResearchProject> project66List;

  public List<ResearchProject> getProject66List()
  { 
    log.info("getProject66List");
    return project66List;
  }

  @Factory("project66List") public void initProject66List()
  { 
    log.info("initProject66List");
    project66List = em.createQuery("from " + "ResearchProject").getResultList();
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