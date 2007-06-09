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
    initPerson31List();
    initResearchProject13List();
    initPerson1031List();
    initProject1131List();
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

  public void setPerson12(Person person32)
  { 
    presentation.setSpeaker(person32);
  }

  public void removeResearchProject4(ResearchProject researchProject12)
  { 
    this.getPresentation().getProjects().remove(researchProject12);
  }

  public void addResearchProject4(ResearchProject researchProject12)
  { 
    this.getPresentation().getProjects().add(researchProject12);
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

  private String newPerson31;

  public void setNewPerson31(String p)
  { 
    newPerson31 = p;
  }

  public String getNewPerson31()
  { 
    return newPerson31;
  }

  public void selectPerson31(ValueChangeEvent event)
  { 
    log.info("selectPerson31" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person31 = em.find(Person.class, id);
      setPerson12(person31);
    }
  }

  @DataModel("person31List") private Map<String, String> person31List;

  public Map<String, String> getPerson31List()
  { 
    return person31List;
  }

  @Factory("person31List") public void initPerson31List()
  { 
    log.info("initPerson31List");
    person31List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person31List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject13;

  public void setNewResearchProject13(String p)
  { 
    newResearchProject13 = p;
  }

  public String getNewResearchProject13()
  { 
    return newResearchProject13;
  }

  public void selectResearchProject13(ValueChangeEvent event)
  { 
    log.info("selectResearchProject13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject13 = em.find(ResearchProject.class, id);
      addResearchProject4(researchProject13);
    }
  }

  @DataModel("researchProject13List") private Map<String, String> researchProject13List;

  public Map<String, String> getResearchProject13List()
  { 
    return researchProject13List;
  }

  @Factory("researchProject13List") public void initResearchProject13List()
  { 
    log.info("initResearchProject13List");
    researchProject13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject13List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1031List") private List<Person> person1031List;

  public List<Person> getPerson1031List()
  { 
    log.info("getPerson1031List");
    return person1031List;
  }

  @Factory("person1031List") public void initPerson1031List()
  { 
    log.info("initPerson1031List");
    person1031List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1131List") private List<ResearchProject> project1131List;

  public List<ResearchProject> getProject1131List()
  { 
    log.info("getProject1131List");
    return project1131List;
  }

  @Factory("project1131List") public void initProject1131List()
  { 
    log.info("initProject1131List");
    project1131List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}