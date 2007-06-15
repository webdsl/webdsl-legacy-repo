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

@Stateful @Name("createPresentation") public class CreatePresentationBean  implements CreatePresentationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createPresentation" + ".initalize()");
    Presentation var26 = new Presentation();
    presentation = var26;
    initPerson146List();
    initResearchProject12List();
    initPerson55List();
    initProject50List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson10(Person person147)
  { 
    presentation.setSpeaker(person147);
  }

  public void removeResearchProject3(ResearchProject researchProject11)
  { 
    this.getPresentation().getProjects().remove(researchProject11);
  }

  public void addResearchProject3(ResearchProject researchProject11)
  { 
    this.getPresentation().getProjects().add(researchProject11);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getPresentation());
    return "/" + "viewPresentation" + ".seam?" + ("presentation" + "=" + presentation.getId() + "");
  }

  private String newPerson146;

  public void setNewPerson146(String p)
  { 
    newPerson146 = p;
  }

  public String getNewPerson146()
  { 
    return newPerson146;
  }

  public void selectPerson146(ValueChangeEvent event)
  { 
    log.info("selectPerson146" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person146 = em.find(Person.class, id);
      setPerson10(person146);
    }
  }

  @DataModel("person146List") private Map<String, String> person146List;

  public Map<String, String> getPerson146List()
  { 
    return person146List;
  }

  @Factory("person146List") public void initPerson146List()
  { 
    log.info("initPerson146List");
    person146List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person146List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject12;

  public void setNewResearchProject12(String p)
  { 
    newResearchProject12 = p;
  }

  public String getNewResearchProject12()
  { 
    return newResearchProject12;
  }

  public void selectResearchProject12(ValueChangeEvent event)
  { 
    log.info("selectResearchProject12" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject12 = em.find(ResearchProject.class, id);
      addResearchProject3(researchProject12);
    }
  }

  @DataModel("researchProject12List") private Map<String, String> researchProject12List;

  public Map<String, String> getResearchProject12List()
  { 
    return researchProject12List;
  }

  @Factory("researchProject12List") public void initResearchProject12List()
  { 
    log.info("initResearchProject12List");
    researchProject12List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject12List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person55List") private List<Person> person55List;

  public List<Person> getPerson55List()
  { 
    log.info("getPerson55List");
    return person55List;
  }

  @Factory("person55List") public void initPerson55List()
  { 
    log.info("initPerson55List");
    person55List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project50List") private List<ResearchProject> project50List;

  public List<ResearchProject> getProject50List()
  { 
    log.info("getProject50List");
    return project50List;
  }

  @Factory("project50List") public void initProject50List()
  { 
    log.info("initProject50List");
    project50List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Presentation presentation;

  public Presentation getPresentation()
  { 
    log.info("getPresentation");
    return presentation;
  }

  public void setPresentation(Presentation presentation)
  { 
    log.info("setPresentation");
    this.presentation = presentation;
  }
}