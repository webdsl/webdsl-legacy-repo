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
    initPerson165List();
    initResearchProject21List();
    initPerson60List();
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

  public void setPerson9(Person person166)
  { 
    presentation.setSpeaker(person166);
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

  private String newPerson165;

  public void setNewPerson165(String p)
  { 
    newPerson165 = p;
  }

  public String getNewPerson165()
  { 
    return newPerson165;
  }

  public void selectPerson165(ValueChangeEvent event)
  { 
    log.info("selectPerson165" + ": new value = " + " " + event.getNewValue());
    Person person165 = (Person)event.getNewValue();
  }

  @DataModel("person165List") private Map<String, String> person165List;

  public Map<String, String> getPerson165List()
  { 
    return person165List;
  }

  @Factory("person165List") public void initPerson165List()
  { 
    log.info("initPerson165List");
    person165List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person165List.put(p.getName(), p.getId().toString());
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
    ResearchProject researchProject21 = (ResearchProject)event.getNewValue();
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