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

@Stateful @Name("editColloquium") public class EditColloquiumBean  implements EditColloquiumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editColloquium" + ".initalize()");
    if(colloquiumId == null)
    { 
      log.info("No " + "colloquiumId" + " defined, creating new " + "Colloquium");
      colloquium = new Colloquium();
    }
    else
    { 
      colloquium = em.find(Colloquium.class, colloquiumId);
    }
    initPerson175List();
    initResearchGroup8List();
    initResearchProject16List();
    initPresentation8List();
    initPerson59List();
    initProject46List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("colloquium") private Long colloquiumId;

  private Colloquium colloquium;

  public void setColloquium(Colloquium colloquium)
  { 
    log.info("setColloquium");
    this.colloquium = colloquium;
  }

  public Colloquium getColloquium()
  { 
    log.info("getColloquium");
    return colloquium;
  }

  public void setPerson7(Person person176)
  { 
    colloquium.setContact(person176);
  }

  public void setResearchGroup0(ResearchGroup researchGroup9)
  { 
    colloquium.setGroup(researchGroup9);
  }

  public void removeResearchProject0(ResearchProject researchProject15)
  { 
    this.getColloquium().getProjects().remove(researchProject15);
  }

  public void addResearchProject0(ResearchProject researchProject15)
  { 
    this.getColloquium().getProjects().add(researchProject15);
  }

  public void removePresentation0(Presentation presentation7)
  { 
    this.getColloquium().getPresentations().remove(presentation7);
  }

  public void addPresentation0(Presentation presentation7)
  { 
    this.getColloquium().getPresentations().add(presentation7);
  }

  @End public String cancel()
  { 
    return "/" + "viewColloquium" + ".seam?" + ("colloquium" + "=" + colloquium.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getColloquium());
    return "/" + "viewColloquium" + ".seam?" + ("colloquium" + "=" + colloquium.getId() + "");
  }

  private String newPerson175;

  public void setNewPerson175(String p)
  { 
    newPerson175 = p;
  }

  public String getNewPerson175()
  { 
    return newPerson175;
  }

  public void selectPerson175(ValueChangeEvent event)
  { 
    log.info("selectPerson175" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person175 = em.find(Person.class, id);
      setPerson7(person175);
    }
  }

  @DataModel("person175List") private Map<String, String> person175List;

  public Map<String, String> getPerson175List()
  { 
    return person175List;
  }

  @Factory("person175List") public void initPerson175List()
  { 
    log.info("initPerson175List");
    person175List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person175List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchGroup8;

  public void setNewResearchGroup8(String p)
  { 
    newResearchGroup8 = p;
  }

  public String getNewResearchGroup8()
  { 
    return newResearchGroup8;
  }

  public void selectResearchGroup8(ValueChangeEvent event)
  { 
    log.info("selectResearchGroup8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchGroup researchGroup8 = em.find(ResearchGroup.class, id);
      setResearchGroup0(researchGroup8);
    }
  }

  @DataModel("researchGroup8List") private Map<String, String> researchGroup8List;

  public Map<String, String> getResearchGroup8List()
  { 
    return researchGroup8List;
  }

  @Factory("researchGroup8List") public void initResearchGroup8List()
  { 
    log.info("initResearchGroup8List");
    researchGroup8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchGroup").getResultList())
    { 
      ResearchGroup p = (ResearchGroup)o;
      researchGroup8List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject16;

  public void setNewResearchProject16(String p)
  { 
    newResearchProject16 = p;
  }

  public String getNewResearchProject16()
  { 
    return newResearchProject16;
  }

  public void selectResearchProject16(ValueChangeEvent event)
  { 
    log.info("selectResearchProject16" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject16 = em.find(ResearchProject.class, id);
      addResearchProject0(researchProject16);
    }
  }

  @DataModel("researchProject16List") private Map<String, String> researchProject16List;

  public Map<String, String> getResearchProject16List()
  { 
    return researchProject16List;
  }

  @Factory("researchProject16List") public void initResearchProject16List()
  { 
    log.info("initResearchProject16List");
    researchProject16List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject16List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPresentation8;

  public void setNewPresentation8(String p)
  { 
    newPresentation8 = p;
  }

  public String getNewPresentation8()
  { 
    return newPresentation8;
  }

  public void selectPresentation8(ValueChangeEvent event)
  { 
    log.info("selectPresentation8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Presentation presentation8 = em.find(Presentation.class, id);
      addPresentation0(presentation8);
    }
  }

  @DataModel("presentation8List") private Map<String, String> presentation8List;

  public Map<String, String> getPresentation8List()
  { 
    return presentation8List;
  }

  @Factory("presentation8List") public void initPresentation8List()
  { 
    log.info("initPresentation8List");
    presentation8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Presentation").getResultList())
    { 
      Presentation p = (Presentation)o;
      presentation8List.put(p.getName(), p.getId().toString());
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
}