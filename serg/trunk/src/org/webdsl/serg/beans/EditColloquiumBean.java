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
    initPerson129List();
    initResearchGroup4List();
    initResearchProject8List();
    initPresentation7List();
    initPerson49List();
    initProject44List();
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

  public void setPerson9(Person person130)
  { 
    colloquium.setContact(person130);
  }

  public void setResearchGroup0(ResearchGroup researchGroup5)
  { 
    colloquium.setGroup(researchGroup5);
  }

  public void removeResearchProject1(ResearchProject researchProject7)
  { 
    this.getColloquium().getProjects().remove(researchProject7);
  }

  public void addResearchProject1(ResearchProject researchProject7)
  { 
    this.getColloquium().getProjects().add(researchProject7);
  }

  public void removePresentation0(Presentation presentation6)
  { 
    this.getColloquium().getPresentations().remove(presentation6);
  }

  public void addPresentation0(Presentation presentation6)
  { 
    this.getColloquium().getPresentations().add(presentation6);
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

  private String newPerson129;

  public void setNewPerson129(String p)
  { 
    newPerson129 = p;
  }

  public String getNewPerson129()
  { 
    return newPerson129;
  }

  public void selectPerson129(ValueChangeEvent event)
  { 
    log.info("selectPerson129" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person129 = em.find(Person.class, id);
      setPerson9(person129);
    }
  }

  @DataModel("person129List") private Map<String, String> person129List;

  public Map<String, String> getPerson129List()
  { 
    return person129List;
  }

  @Factory("person129List") public void initPerson129List()
  { 
    log.info("initPerson129List");
    person129List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person129List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchGroup4;

  public void setNewResearchGroup4(String p)
  { 
    newResearchGroup4 = p;
  }

  public String getNewResearchGroup4()
  { 
    return newResearchGroup4;
  }

  public void selectResearchGroup4(ValueChangeEvent event)
  { 
    log.info("selectResearchGroup4" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchGroup researchGroup4 = em.find(ResearchGroup.class, id);
      setResearchGroup0(researchGroup4);
    }
  }

  @DataModel("researchGroup4List") private Map<String, String> researchGroup4List;

  public Map<String, String> getResearchGroup4List()
  { 
    return researchGroup4List;
  }

  @Factory("researchGroup4List") public void initResearchGroup4List()
  { 
    log.info("initResearchGroup4List");
    researchGroup4List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchGroup").getResultList())
    { 
      ResearchGroup p = (ResearchGroup)o;
      researchGroup4List.put(p.getName(), p.getId().toString());
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

  private String newPresentation7;

  public void setNewPresentation7(String p)
  { 
    newPresentation7 = p;
  }

  public String getNewPresentation7()
  { 
    return newPresentation7;
  }

  public void selectPresentation7(ValueChangeEvent event)
  { 
    log.info("selectPresentation7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Presentation presentation7 = em.find(Presentation.class, id);
      addPresentation0(presentation7);
    }
  }

  @DataModel("presentation7List") private Map<String, String> presentation7List;

  public Map<String, String> getPresentation7List()
  { 
    return presentation7List;
  }

  @Factory("presentation7List") public void initPresentation7List()
  { 
    log.info("initPresentation7List");
    presentation7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Presentation").getResultList())
    { 
      Presentation p = (Presentation)o;
      presentation7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person49List") private List<Person> person49List;

  public List<Person> getPerson49List()
  { 
    log.info("getPerson49List");
    return person49List;
  }

  @Factory("person49List") public void initPerson49List()
  { 
    log.info("initPerson49List");
    person49List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project44List") private List<ResearchProject> project44List;

  public List<ResearchProject> getProject44List()
  { 
    log.info("getProject44List");
    return project44List;
  }

  @Factory("project44List") public void initProject44List()
  { 
    log.info("initProject44List");
    project44List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}