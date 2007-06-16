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
    initPerson155List();
    initResearchGroup8List();
    initResearchProject15List();
    initPresentation8List();
    initPerson56List();
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

  public void setPerson7(Person person156)
  { 
    colloquium.setContact(person156);
  }

  public void setResearchGroup0(ResearchGroup researchGroup9)
  { 
    colloquium.setGroup(researchGroup9);
  }

  public void removeResearchProject0(ResearchProject researchProject14)
  { 
    this.getColloquium().getProjects().remove(researchProject14);
  }

  public void addResearchProject0(ResearchProject researchProject14)
  { 
    this.getColloquium().getProjects().add(researchProject14);
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

  private String newPerson155;

  public void setNewPerson155(String p)
  { 
    newPerson155 = p;
  }

  public String getNewPerson155()
  { 
    return newPerson155;
  }

  public void selectPerson155(ValueChangeEvent event)
  { 
    log.info("selectPerson155" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person155 = em.find(Person.class, id);
      setPerson7(person155);
    }
  }

  @DataModel("person155List") private Map<String, String> person155List;

  public Map<String, String> getPerson155List()
  { 
    return person155List;
  }

  @Factory("person155List") public void initPerson155List()
  { 
    log.info("initPerson155List");
    person155List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person155List.put(p.getName(), p.getId().toString());
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
      addResearchProject0(researchProject15);
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

  @DataModel("person56List") private List<Person> person56List;

  public List<Person> getPerson56List()
  { 
    log.info("getPerson56List");
    return person56List;
  }

  @Factory("person56List") public void initPerson56List()
  { 
    log.info("initPerson56List");
    person56List = em.createQuery("from " + "Person").getResultList();
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