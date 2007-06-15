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

@Stateful @Name("createColloquium") public class CreateColloquiumBean  implements CreateColloquiumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createColloquium" + ".initalize()");
    Colloquium var23 = new Colloquium();
    colloquium = var23;
    initPerson146List();
    initResearchGroup6List();
    initResearchProject13List();
    initPresentation9List();
    initPerson51List();
    initProject46List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson7(Person person147)
  { 
    colloquium.setContact(person147);
  }

  public void setResearchGroup1(ResearchGroup researchGroup7)
  { 
    colloquium.setGroup(researchGroup7);
  }

  public void removeResearchProject1(ResearchProject researchProject12)
  { 
    this.getColloquium().getProjects().remove(researchProject12);
  }

  public void addResearchProject1(ResearchProject researchProject12)
  { 
    this.getColloquium().getProjects().add(researchProject12);
  }

  public void removePresentation1(Presentation presentation8)
  { 
    this.getColloquium().getPresentations().remove(presentation8);
  }

  public void addPresentation1(Presentation presentation8)
  { 
    this.getColloquium().getPresentations().add(presentation8);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getColloquium());
    return "/" + "viewColloquium" + ".seam?" + ("colloquium" + "=" + colloquium.getId() + "");
  }

  private String newPerson146;

  public void setNewPerson146(String p)
  { 
    newPerson146 = p;
  }

  public String getNewPerson146()
  { 
    return newPerson146;
  }

  public void selectPerson146(ValueChangeEvent event)
  { 
    log.info("selectPerson146" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person146 = em.find(Person.class, id);
      setPerson7(person146);
    }
  }

  @DataModel("person146List") private Map<String, String> person146List;

  public Map<String, String> getPerson146List()
  { 
    return person146List;
  }

  @Factory("person146List") public void initPerson146List()
  { 
    log.info("initPerson146List");
    person146List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person146List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchGroup6;

  public void setNewResearchGroup6(String p)
  { 
    newResearchGroup6 = p;
  }

  public String getNewResearchGroup6()
  { 
    return newResearchGroup6;
  }

  public void selectResearchGroup6(ValueChangeEvent event)
  { 
    log.info("selectResearchGroup6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchGroup researchGroup6 = em.find(ResearchGroup.class, id);
      setResearchGroup1(researchGroup6);
    }
  }

  @DataModel("researchGroup6List") private Map<String, String> researchGroup6List;

  public Map<String, String> getResearchGroup6List()
  { 
    return researchGroup6List;
  }

  @Factory("researchGroup6List") public void initResearchGroup6List()
  { 
    log.info("initResearchGroup6List");
    researchGroup6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchGroup").getResultList())
    { 
      ResearchGroup p = (ResearchGroup)o;
      researchGroup6List.put(p.getName(), p.getId().toString());
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
      addResearchProject1(researchProject13);
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

  private String newPresentation9;

  public void setNewPresentation9(String p)
  { 
    newPresentation9 = p;
  }

  public String getNewPresentation9()
  { 
    return newPresentation9;
  }

  public void selectPresentation9(ValueChangeEvent event)
  { 
    log.info("selectPresentation9" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Presentation presentation9 = em.find(Presentation.class, id);
      addPresentation1(presentation9);
    }
  }

  @DataModel("presentation9List") private Map<String, String> presentation9List;

  public Map<String, String> getPresentation9List()
  { 
    return presentation9List;
  }

  @Factory("presentation9List") public void initPresentation9List()
  { 
    log.info("initPresentation9List");
    presentation9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Presentation").getResultList())
    { 
      Presentation p = (Presentation)o;
      presentation9List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person51List") private List<Person> person51List;

  public List<Person> getPerson51List()
  { 
    log.info("getPerson51List");
    return person51List;
  }

  @Factory("person51List") public void initPerson51List()
  { 
    log.info("initPerson51List");
    person51List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project46List") private List<ResearchProject> project46List;

  public List<ResearchProject> getProject46List()
  { 
    log.info("getProject46List");
    return project46List;
  }

  @Factory("project46List") public void initProject46List()
  { 
    log.info("initProject46List");
    project46List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Colloquium colloquium;

  public Colloquium getColloquium()
  { 
    log.info("getColloquium");
    return colloquium;
  }

  public void setColloquium(Colloquium colloquium)
  { 
    log.info("setColloquium");
    this.colloquium = colloquium;
  }
}