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
    Colloquium var29 = new Colloquium();
    colloquium = var29;
    initPerson177List();
    initResearchGroup10List();
    initResearchProject18List();
    initPresentation10List();
    initPerson60List();
    initProject47List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson8(Person person178)
  { 
    colloquium.setContact(person178);
  }

  public void setResearchGroup1(ResearchGroup researchGroup11)
  { 
    colloquium.setGroup(researchGroup11);
  }

  public void removeResearchProject1(ResearchProject researchProject17)
  { 
    this.getColloquium().getProjects().remove(researchProject17);
  }

  public void addResearchProject1(ResearchProject researchProject17)
  { 
    this.getColloquium().getProjects().add(researchProject17);
  }

  public void removePresentation1(Presentation presentation9)
  { 
    this.getColloquium().getPresentations().remove(presentation9);
  }

  public void addPresentation1(Presentation presentation9)
  { 
    this.getColloquium().getPresentations().add(presentation9);
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

  private String newPerson177;

  public void setNewPerson177(String p)
  { 
    newPerson177 = p;
  }

  public String getNewPerson177()
  { 
    return newPerson177;
  }

  public void selectPerson177(ValueChangeEvent event)
  { 
    log.info("selectPerson177" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person177 = em.find(Person.class, id);
      setPerson8(person177);
    }
  }

  @DataModel("person177List") private Map<String, String> person177List;

  public Map<String, String> getPerson177List()
  { 
    return person177List;
  }

  @Factory("person177List") public void initPerson177List()
  { 
    log.info("initPerson177List");
    person177List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person177List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchGroup10;

  public void setNewResearchGroup10(String p)
  { 
    newResearchGroup10 = p;
  }

  public String getNewResearchGroup10()
  { 
    return newResearchGroup10;
  }

  public void selectResearchGroup10(ValueChangeEvent event)
  { 
    log.info("selectResearchGroup10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchGroup researchGroup10 = em.find(ResearchGroup.class, id);
      setResearchGroup1(researchGroup10);
    }
  }

  @DataModel("researchGroup10List") private Map<String, String> researchGroup10List;

  public Map<String, String> getResearchGroup10List()
  { 
    return researchGroup10List;
  }

  @Factory("researchGroup10List") public void initResearchGroup10List()
  { 
    log.info("initResearchGroup10List");
    researchGroup10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchGroup").getResultList())
    { 
      ResearchGroup p = (ResearchGroup)o;
      researchGroup10List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject18;

  public void setNewResearchProject18(String p)
  { 
    newResearchProject18 = p;
  }

  public String getNewResearchProject18()
  { 
    return newResearchProject18;
  }

  public void selectResearchProject18(ValueChangeEvent event)
  { 
    log.info("selectResearchProject18" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject18 = em.find(ResearchProject.class, id);
      addResearchProject1(researchProject18);
    }
  }

  @DataModel("researchProject18List") private Map<String, String> researchProject18List;

  public Map<String, String> getResearchProject18List()
  { 
    return researchProject18List;
  }

  @Factory("researchProject18List") public void initResearchProject18List()
  { 
    log.info("initResearchProject18List");
    researchProject18List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject18List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPresentation10;

  public void setNewPresentation10(String p)
  { 
    newPresentation10 = p;
  }

  public String getNewPresentation10()
  { 
    return newPresentation10;
  }

  public void selectPresentation10(ValueChangeEvent event)
  { 
    log.info("selectPresentation10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Presentation presentation10 = em.find(Presentation.class, id);
      addPresentation1(presentation10);
    }
  }

  @DataModel("presentation10List") private Map<String, String> presentation10List;

  public Map<String, String> getPresentation10List()
  { 
    return presentation10List;
  }

  @Factory("presentation10List") public void initPresentation10List()
  { 
    log.info("initPresentation10List");
    presentation10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Presentation").getResultList())
    { 
      Presentation p = (Presentation)o;
      presentation10List.put(p.getName(), p.getId().toString());
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

  @DataModel("project47List") private List<ResearchProject> project47List;

  public List<ResearchProject> getProject47List()
  { 
    log.info("getProject47List");
    return project47List;
  }

  @Factory("project47List") public void initProject47List()
  { 
    log.info("initProject47List");
    project47List = em.createQuery("from " + "ResearchProject").getResultList();
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