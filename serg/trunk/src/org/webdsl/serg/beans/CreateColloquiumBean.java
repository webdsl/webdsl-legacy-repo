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
    initPerson131List();
    initResearchGroup6List();
    initResearchProject10List();
    initPresentation9List();
    initPerson50List();
    initProject45List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson10(Person person132)
  { 
    colloquium.setContact(person132);
  }

  public void setResearchGroup1(ResearchGroup researchGroup7)
  { 
    colloquium.setGroup(researchGroup7);
  }

  public void removeResearchProject2(ResearchProject researchProject9)
  { 
    this.getColloquium().getProjects().remove(researchProject9);
  }

  public void addResearchProject2(ResearchProject researchProject9)
  { 
    this.getColloquium().getProjects().add(researchProject9);
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

  private String newPerson131;

  public void setNewPerson131(String p)
  { 
    newPerson131 = p;
  }

  public String getNewPerson131()
  { 
    return newPerson131;
  }

  public void selectPerson131(ValueChangeEvent event)
  { 
    log.info("selectPerson131" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person131 = em.find(Person.class, id);
      setPerson10(person131);
    }
  }

  @DataModel("person131List") private Map<String, String> person131List;

  public Map<String, String> getPerson131List()
  { 
    return person131List;
  }

  @Factory("person131List") public void initPerson131List()
  { 
    log.info("initPerson131List");
    person131List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person131List.put(p.getName(), p.getId().toString());
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

  @DataModel("person50List") private List<Person> person50List;

  public List<Person> getPerson50List()
  { 
    log.info("getPerson50List");
    return person50List;
  }

  @Factory("person50List") public void initPerson50List()
  { 
    log.info("initPerson50List");
    person50List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project45List") private List<ResearchProject> project45List;

  public List<ResearchProject> getProject45List()
  { 
    log.info("getProject45List");
    return project45List;
  }

  @Factory("project45List") public void initProject45List()
  { 
    log.info("initProject45List");
    project45List = em.createQuery("from " + "ResearchProject").getResultList();
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