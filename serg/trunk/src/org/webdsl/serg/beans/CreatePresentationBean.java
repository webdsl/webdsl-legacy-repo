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
    Presentation var24 = new Presentation();
    presentation = var24;
    initPerson150List();
    initResearchProject17List();
    initPerson54List();
    initProject49List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson9(Person person151)
  { 
    presentation.setSpeaker(person151);
  }

  public void removeResearchProject3(ResearchProject researchProject16)
  { 
    this.getPresentation().getProjects().remove(researchProject16);
  }

  public void addResearchProject3(ResearchProject researchProject16)
  { 
    this.getPresentation().getProjects().add(researchProject16);
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

  private String newPerson150;

  public void setNewPerson150(String p)
  { 
    newPerson150 = p;
  }

  public String getNewPerson150()
  { 
    return newPerson150;
  }

  public void selectPerson150(ValueChangeEvent event)
  { 
    log.info("selectPerson150" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person150 = em.find(Person.class, id);
      setPerson9(person150);
    }
  }

  @DataModel("person150List") private Map<String, String> person150List;

  public Map<String, String> getPerson150List()
  { 
    return person150List;
  }

  @Factory("person150List") public void initPerson150List()
  { 
    log.info("initPerson150List");
    person150List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person150List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject17;

  public void setNewResearchProject17(String p)
  { 
    newResearchProject17 = p;
  }

  public String getNewResearchProject17()
  { 
    return newResearchProject17;
  }

  public void selectResearchProject17(ValueChangeEvent event)
  { 
    log.info("selectResearchProject17" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject17 = em.find(ResearchProject.class, id);
      addResearchProject3(researchProject17);
    }
  }

  @DataModel("researchProject17List") private Map<String, String> researchProject17List;

  public Map<String, String> getResearchProject17List()
  { 
    return researchProject17List;
  }

  @Factory("researchProject17List") public void initResearchProject17List()
  { 
    log.info("initResearchProject17List");
    researchProject17List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject17List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person54List") private List<Person> person54List;

  public List<Person> getPerson54List()
  { 
    log.info("getPerson54List");
    return person54List;
  }

  @Factory("person54List") public void initPerson54List()
  { 
    log.info("initPerson54List");
    person54List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project49List") private List<ResearchProject> project49List;

  public List<ResearchProject> getProject49List()
  { 
    log.info("getProject49List");
    return project49List;
  }

  @Factory("project49List") public void initProject49List()
  { 
    log.info("initProject49List");
    project49List = em.createQuery("from " + "ResearchProject").getResultList();
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