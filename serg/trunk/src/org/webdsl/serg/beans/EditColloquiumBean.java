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
    initPerson140List();
    initResearchGroup4List();
    initResearchProject6List();
    initPresentation8List();
    initPerson51List();
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

  public void setPerson7(Person person141)
  { 
    colloquium.setContact(person141);
  }

  public void setResearchGroup0(ResearchGroup researchGroup5)
  { 
    colloquium.setGroup(researchGroup5);
  }

  public void removeResearchProject0(ResearchProject researchProject5)
  { 
    this.getColloquium().getProjects().remove(researchProject5);
  }

  public void addResearchProject0(ResearchProject researchProject5)
  { 
    this.getColloquium().getProjects().add(researchProject5);
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

  private String newPerson140;

  public void setNewPerson140(String p)
  { 
    newPerson140 = p;
  }

  public String getNewPerson140()
  { 
    return newPerson140;
  }

  public void selectPerson140(ValueChangeEvent event)
  { 
    log.info("selectPerson140" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person140 = em.find(Person.class, id);
      setPerson7(person140);
    }
  }

  @DataModel("person140List") private Map<String, String> person140List;

  public Map<String, String> getPerson140List()
  { 
    return person140List;
  }

  @Factory("person140List") public void initPerson140List()
  { 
    log.info("initPerson140List");
    person140List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person140List.put(p.getName(), p.getId().toString());
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

  private String newResearchProject6;

  public void setNewResearchProject6(String p)
  { 
    newResearchProject6 = p;
  }

  public String getNewResearchProject6()
  { 
    return newResearchProject6;
  }

  public void selectResearchProject6(ValueChangeEvent event)
  { 
    log.info("selectResearchProject6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject6 = em.find(ResearchProject.class, id);
      addResearchProject0(researchProject6);
    }
  }

  @DataModel("researchProject6List") private Map<String, String> researchProject6List;

  public Map<String, String> getResearchProject6List()
  { 
    return researchProject6List;
  }

  @Factory("researchProject6List") public void initResearchProject6List()
  { 
    log.info("initResearchProject6List");
    researchProject6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject6List.put(p.getName(), p.getId().toString());
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
}