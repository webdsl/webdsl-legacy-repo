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
    Presentation var30 = new Presentation();
    presentation = var30;
    initPerson181List();
    initResearchProject23List();
    initPerson63List();
    initProject50List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson10(Person person182)
  { 
    presentation.setSpeaker(person182);
  }

  public void removeResearchProject3(ResearchProject researchProject22)
  { 
    this.getPresentation().getProjects().remove(researchProject22);
  }

  public void addResearchProject3(ResearchProject researchProject22)
  { 
    this.getPresentation().getProjects().add(researchProject22);
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

  private String newPerson181;

  public void setNewPerson181(String p)
  { 
    newPerson181 = p;
  }

  public String getNewPerson181()
  { 
    return newPerson181;
  }

  public void selectPerson181(ValueChangeEvent event)
  { 
    log.info("selectPerson181" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person181 = em.find(Person.class, id);
      setPerson10(person181);
    }
  }

  @DataModel("person181List") private Map<String, String> person181List;

  public Map<String, String> getPerson181List()
  { 
    return person181List;
  }

  @Factory("person181List") public void initPerson181List()
  { 
    log.info("initPerson181List");
    person181List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person181List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject23;

  public void setNewResearchProject23(String p)
  { 
    newResearchProject23 = p;
  }

  public String getNewResearchProject23()
  { 
    return newResearchProject23;
  }

  public void selectResearchProject23(ValueChangeEvent event)
  { 
    log.info("selectResearchProject23" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject23 = em.find(ResearchProject.class, id);
      addResearchProject3(researchProject23);
    }
  }

  @DataModel("researchProject23List") private Map<String, String> researchProject23List;

  public Map<String, String> getResearchProject23List()
  { 
    return researchProject23List;
  }

  @Factory("researchProject23List") public void initResearchProject23List()
  { 
    log.info("initResearchProject23List");
    researchProject23List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject23List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person63List") private List<Person> person63List;

  public List<Person> getPerson63List()
  { 
    log.info("getPerson63List");
    return person63List;
  }

  @Factory("person63List") public void initPerson63List()
  { 
    log.info("initPerson63List");
    person63List = em.createQuery("from " + "Person").getResultList();
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