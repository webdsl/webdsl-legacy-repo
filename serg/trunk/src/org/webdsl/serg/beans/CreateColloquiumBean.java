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
    Colloquium var25 = new Colloquium();
    colloquium = var25;
    initPerson142List();
    initResearchGroup6List();
    initResearchProject8List();
    initPresentation10List();
    initPerson52List();
    initProject47List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson8(Person person143)
  { 
    colloquium.setContact(person143);
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

  private String newPerson142;

  public void setNewPerson142(String p)
  { 
    newPerson142 = p;
  }

  public String getNewPerson142()
  { 
    return newPerson142;
  }

  public void selectPerson142(ValueChangeEvent event)
  { 
    log.info("selectPerson142" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person142 = em.find(Person.class, id);
      setPerson8(person142);
    }
  }

  @DataModel("person142List") private Map<String, String> person142List;

  public Map<String, String> getPerson142List()
  { 
    return person142List;
  }

  @Factory("person142List") public void initPerson142List()
  { 
    log.info("initPerson142List");
    person142List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person142List.put(p.getName(), p.getId().toString());
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

  @DataModel("person52List") private List<Person> person52List;

  public List<Person> getPerson52List()
  { 
    log.info("getPerson52List");
    return person52List;
  }

  @Factory("person52List") public void initPerson52List()
  { 
    log.info("initPerson52List");
    person52List = em.createQuery("from " + "Person").getResultList();
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