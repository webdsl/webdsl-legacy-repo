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
    initPerson124List();
    initResearchGroup6List();
    initResearchProject8List();
    initPresentation9List();
    initPerson51List();
    initProject46List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson7(Person person125)
  { 
    colloquium.setContact(person125);
  }

  public void setResearchGroup1(ResearchGroup researchGroup7)
  { 
    colloquium.setGroup(researchGroup7);
  }

  public void removeResearchProject1(ResearchProject researchProject7)
  { 
    this.getColloquium().getProjects().remove(researchProject7);
  }

  public void addResearchProject1(ResearchProject researchProject7)
  { 
    this.getColloquium().getProjects().add(researchProject7);
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

  private String newPerson124;

  public void setNewPerson124(String p)
  { 
    newPerson124 = p;
  }

  public String getNewPerson124()
  { 
    return newPerson124;
  }

  public void selectPerson124(ValueChangeEvent event)
  { 
    log.info("selectPerson124" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person124 = em.find(Person.class, id);
      setPerson7(person124);
    }
  }

  @DataModel("person124List") private Map<String, String> person124List;

  public Map<String, String> getPerson124List()
  { 
    return person124List;
  }

  @Factory("person124List") public void initPerson124List()
  { 
    log.info("initPerson124List");
    person124List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person124List.put(p.getName(), p.getId().toString());
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

  private String newResearchProject8;

  public void setNewResearchProject8(String p)
  { 
    newResearchProject8 = p;
  }

  public String getNewResearchProject8()
  { 
    return newResearchProject8;
  }

  public void selectResearchProject8(ValueChangeEvent event)
  { 
    log.info("selectResearchProject8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject8 = em.find(ResearchProject.class, id);
      addResearchProject1(researchProject8);
    }
  }

  @DataModel("researchProject8List") private Map<String, String> researchProject8List;

  public Map<String, String> getResearchProject8List()
  { 
    return researchProject8List;
  }

  @Factory("researchProject8List") public void initResearchProject8List()
  { 
    log.info("initResearchProject8List");
    researchProject8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject8List.put(p.getName(), p.getId().toString());
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