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
    initPerson144List();
    initResearchProject10List();
    initPerson54List();
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

  public void setPerson9(Person person145)
  { 
    presentation.setSpeaker(person145);
  }

  public void removeResearchProject2(ResearchProject researchProject9)
  { 
    this.getPresentation().getProjects().remove(researchProject9);
  }

  public void addResearchProject2(ResearchProject researchProject9)
  { 
    this.getPresentation().getProjects().add(researchProject9);
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

  private String newPerson144;

  public void setNewPerson144(String p)
  { 
    newPerson144 = p;
  }

  public String getNewPerson144()
  { 
    return newPerson144;
  }

  public void selectPerson144(ValueChangeEvent event)
  { 
    log.info("selectPerson144" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person144 = em.find(Person.class, id);
      setPerson9(person144);
    }
  }

  @DataModel("person144List") private Map<String, String> person144List;

  public Map<String, String> getPerson144List()
  { 
    return person144List;
  }

  @Factory("person144List") public void initPerson144List()
  { 
    log.info("initPerson144List");
    person144List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person144List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject10;

  public void setNewResearchProject10(String p)
  { 
    newResearchProject10 = p;
  }

  public String getNewResearchProject10()
  { 
    return newResearchProject10;
  }

  public void selectResearchProject10(ValueChangeEvent event)
  { 
    log.info("selectResearchProject10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject10 = em.find(ResearchProject.class, id);
      addResearchProject2(researchProject10);
    }
  }

  @DataModel("researchProject10List") private Map<String, String> researchProject10List;

  public Map<String, String> getResearchProject10List()
  { 
    return researchProject10List;
  }

  @Factory("researchProject10List") public void initResearchProject10List()
  { 
    log.info("initResearchProject10List");
    researchProject10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject10List.put(p.getName(), p.getId().toString());
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
}