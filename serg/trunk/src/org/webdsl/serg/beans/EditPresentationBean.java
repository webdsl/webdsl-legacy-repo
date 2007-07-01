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

@Stateful @Name("editPresentation") public class EditPresentationBean  implements EditPresentationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editPresentation" + ".initalize()");
    if(presentationId == null)
    { 
      log.info("No " + "presentationId" + " defined, creating new " + "Presentation");
      presentation = new Presentation();
    }
    else
    { 
      presentation = em.find(Presentation.class, presentationId);
    }
    initPerson179List();
    initResearchProject21List();
    initPerson62List();
    initProject49List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("presentation") private Long presentationId;

  private Presentation presentation;

  public void setPresentation(Presentation presentation)
  { 
    log.info("setPresentation");
    this.presentation = presentation;
  }

  public Presentation getPresentation()
  { 
    log.info("getPresentation");
    return presentation;
  }

  public void setPerson9(Person person180)
  { 
    presentation.setSpeaker(person180);
  }

  public void removeResearchProject2(ResearchProject researchProject19)
  { 
    this.getPresentation().getProjects().remove(researchProject19);
  }

  public void addResearchProject2(ResearchProject researchProject19)
  { 
    this.getPresentation().getProjects().add(researchProject19);
  }

  @End public String cancel()
  { 
    return "/" + "viewPresentation" + ".seam?" + ("presentation" + "=" + presentation.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getPresentation());
    return "/" + "viewPresentation" + ".seam?" + ("presentation" + "=" + presentation.getId() + "");
  }

  private String newPerson179;

  public void setNewPerson179(String p)
  { 
    newPerson179 = p;
  }

  public String getNewPerson179()
  { 
    return newPerson179;
  }

  public void selectPerson179(ValueChangeEvent event)
  { 
    log.info("selectPerson179" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person179 = em.find(Person.class, id);
      setPerson9(person179);
    }
  }

  @DataModel("person179List") private Map<String, String> person179List;

  public Map<String, String> getPerson179List()
  { 
    return person179List;
  }

  @Factory("person179List") public void initPerson179List()
  { 
    log.info("initPerson179List");
    person179List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person179List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject21;

  public void setNewResearchProject21(String p)
  { 
    newResearchProject21 = p;
  }

  public String getNewResearchProject21()
  { 
    return newResearchProject21;
  }

  public void selectResearchProject21(ValueChangeEvent event)
  { 
    log.info("selectResearchProject21" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject21 = em.find(ResearchProject.class, id);
      addResearchProject2(researchProject21);
    }
  }

  @DataModel("researchProject21List") private Map<String, String> researchProject21List;

  public Map<String, String> getResearchProject21List()
  { 
    return researchProject21List;
  }

  @Factory("researchProject21List") public void initResearchProject21List()
  { 
    log.info("initResearchProject21List");
    researchProject21List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject21List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person62List") private List<Person> person62List;

  public List<Person> getPerson62List()
  { 
    log.info("getPerson62List");
    return person62List;
  }

  @Factory("person62List") public void initPerson62List()
  { 
    log.info("initPerson62List");
    person62List = em.createQuery("from " + "Person").getResultList();
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
}