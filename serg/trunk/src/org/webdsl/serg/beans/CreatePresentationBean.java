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
    initPerson137List();
    initResearchProject17List();
    initPerson53List();
    initProject48List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson13(Person person138)
  { 
    presentation.setSpeaker(person138);
  }

  public void removeResearchProject5(ResearchProject researchProject16)
  { 
    this.getPresentation().getProjects().remove(researchProject16);
  }

  public void addResearchProject5(ResearchProject researchProject16)
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

  private String newPerson137;

  public void setNewPerson137(String p)
  { 
    newPerson137 = p;
  }

  public String getNewPerson137()
  { 
    return newPerson137;
  }

  public void selectPerson137(ValueChangeEvent event)
  { 
    log.info("selectPerson137" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person137 = em.find(Person.class, id);
      setPerson13(person137);
    }
  }

  @DataModel("person137List") private Map<String, String> person137List;

  public Map<String, String> getPerson137List()
  { 
    return person137List;
  }

  @Factory("person137List") public void initPerson137List()
  { 
    log.info("initPerson137List");
    person137List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person137List.put(p.getName(), p.getId().toString());
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
      addResearchProject5(researchProject17);
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

  @DataModel("person53List") private List<Person> person53List;

  public List<Person> getPerson53List()
  { 
    log.info("getPerson53List");
    return person53List;
  }

  @Factory("person53List") public void initPerson53List()
  { 
    log.info("initPerson53List");
    person53List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project48List") private List<ResearchProject> project48List;

  public List<ResearchProject> getProject48List()
  { 
    log.info("getProject48List");
    return project48List;
  }

  @Factory("project48List") public void initProject48List()
  { 
    log.info("initProject48List");
    project48List = em.createQuery("from " + "ResearchProject").getResultList();
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