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
    initPerson33List();
    initResearchProject15List();
    initPerson1032List();
    initProject1132List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson13(Person person34)
  { 
    presentation.setSpeaker(person34);
  }

  public void removeResearchProject5(ResearchProject researchProject14)
  { 
    this.getPresentation().getProjects().remove(researchProject14);
  }

  public void addResearchProject5(ResearchProject researchProject14)
  { 
    this.getPresentation().getProjects().add(researchProject14);
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

  private String newPerson33;

  public void setNewPerson33(String p)
  { 
    newPerson33 = p;
  }

  public String getNewPerson33()
  { 
    return newPerson33;
  }

  public void selectPerson33(ValueChangeEvent event)
  { 
    log.info("selectPerson33" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person33 = em.find(Person.class, id);
      setPerson13(person33);
    }
  }

  @DataModel("person33List") private Map<String, String> person33List;

  public Map<String, String> getPerson33List()
  { 
    return person33List;
  }

  @Factory("person33List") public void initPerson33List()
  { 
    log.info("initPerson33List");
    person33List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person33List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject15;

  public void setNewResearchProject15(String p)
  { 
    newResearchProject15 = p;
  }

  public String getNewResearchProject15()
  { 
    return newResearchProject15;
  }

  public void selectResearchProject15(ValueChangeEvent event)
  { 
    log.info("selectResearchProject15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject15 = em.find(ResearchProject.class, id);
      addResearchProject5(researchProject15);
    }
  }

  @DataModel("researchProject15List") private Map<String, String> researchProject15List;

  public Map<String, String> getResearchProject15List()
  { 
    return researchProject15List;
  }

  @Factory("researchProject15List") public void initResearchProject15List()
  { 
    log.info("initResearchProject15List");
    researchProject15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject15List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1032List") private List<Person> person1032List;

  public List<Person> getPerson1032List()
  { 
    log.info("getPerson1032List");
    return person1032List;
  }

  @Factory("person1032List") public void initPerson1032List()
  { 
    log.info("initPerson1032List");
    person1032List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1132List") private List<ResearchProject> project1132List;

  public List<ResearchProject> getProject1132List()
  { 
    log.info("getProject1132List");
    return project1132List;
  }

  @Factory("project1132List") public void initProject1132List()
  { 
    log.info("initProject1132List");
    project1132List = em.createQuery("from " + "ResearchProject").getResultList();
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