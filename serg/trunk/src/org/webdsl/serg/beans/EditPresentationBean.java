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
    initPerson159List();
    initResearchProject19List();
    initPerson59List();
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

  public void setPerson9(Person person160)
  { 
    presentation.setSpeaker(person160);
  }

  public void removeResearchProject2(ResearchProject researchProject18)
  { 
    this.getPresentation().getProjects().remove(researchProject18);
  }

  public void addResearchProject2(ResearchProject researchProject18)
  { 
    this.getPresentation().getProjects().add(researchProject18);
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
      setPerson9(person159);
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

  private String newResearchProject19;

  public void setNewResearchProject19(String p)
  { 
    newResearchProject19 = p;
  }

  public String getNewResearchProject19()
  { 
    return newResearchProject19;
  }

  public void selectResearchProject19(ValueChangeEvent event)
  { 
    log.info("selectResearchProject19" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject19 = em.find(ResearchProject.class, id);
      addResearchProject2(researchProject19);
    }
  }

  @DataModel("researchProject19List") private Map<String, String> researchProject19List;

  public Map<String, String> getResearchProject19List()
  { 
    return researchProject19List;
  }

  @Factory("researchProject19List") public void initResearchProject19List()
  { 
    log.info("initResearchProject19List");
    researchProject19List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject19List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person59List") private List<Person> person59List;

  public List<Person> getPerson59List()
  { 
    log.info("getPerson59List");
    return person59List;
  }

  @Factory("person59List") public void initPerson59List()
  { 
    log.info("initPerson59List");
    person59List = em.createQuery("from " + "Person").getResultList();
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