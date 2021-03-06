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
    initPerson151List();
    initResearchProject22List();
    initPerson60List();
    initProject50List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson10(Person person152)
  { 
    presentation.setSpeaker(person152);
  }

  public void removeResearchProject3(ResearchProject researchProject21)
  { 
    this.getPresentation().getProjects().remove(researchProject21);
  }

  public void addResearchProject3(ResearchProject researchProject21)
  { 
    this.getPresentation().getProjects().add(researchProject21);
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

  private String newPerson151;

  public void setNewPerson151(String p)
  { 
    newPerson151 = p;
  }

  public String getNewPerson151()
  { 
    return newPerson151;
  }

  public void selectPerson151(ValueChangeEvent event)
  { 
    log.info("selectPerson151" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person151 = em.find(Person.class, id);
      setPerson10(person151);
    }
  }

  @DataModel("person151List") private Map<String, String> person151List;

  public Map<String, String> getPerson151List()
  { 
    return person151List;
  }

  @Factory("person151List") public void initPerson151List()
  { 
    log.info("initPerson151List");
    person151List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person151List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject22;

  public void setNewResearchProject22(String p)
  { 
    newResearchProject22 = p;
  }

  public String getNewResearchProject22()
  { 
    return newResearchProject22;
  }

  public void selectResearchProject22(ValueChangeEvent event)
  { 
    log.info("selectResearchProject22" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject22 = em.find(ResearchProject.class, id);
      addResearchProject3(researchProject22);
    }
  }

  @DataModel("researchProject22List") private Map<String, String> researchProject22List;

  public Map<String, String> getResearchProject22List()
  { 
    return researchProject22List;
  }

  @Factory("researchProject22List") public void initResearchProject22List()
  { 
    log.info("initResearchProject22List");
    researchProject22List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject22List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person60List") private List<Person> person60List;

  public List<Person> getPerson60List()
  { 
    log.info("getPerson60List");
    return person60List;
  }

  @Factory("person60List") public void initPerson60List()
  { 
    log.info("initPerson60List");
    person60List = em.createQuery("from " + "Person").getResultList();
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