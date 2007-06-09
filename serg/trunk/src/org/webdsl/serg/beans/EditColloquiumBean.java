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
    initPerson25List();
    initResearchGroup2List();
    initResearchProject6List();
    initPresentation5List();
    initPerson1029List();
    initProject1129List();
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

  public void setPerson9(Person person26)
  { 
    colloquium.setContact(person26);
  }

  public void setResearchGroup0(ResearchGroup researchGroup3)
  { 
    colloquium.setGroup(researchGroup3);
  }

  public void removeResearchProject1(ResearchProject researchProject5)
  { 
    this.getColloquium().getProjects().remove(researchProject5);
  }

  public void addResearchProject1(ResearchProject researchProject5)
  { 
    this.getColloquium().getProjects().add(researchProject5);
  }

  public void removePresentation0(Presentation presentation4)
  { 
    this.getColloquium().getPresentations().remove(presentation4);
  }

  public void addPresentation0(Presentation presentation4)
  { 
    this.getColloquium().getPresentations().add(presentation4);
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

  private String newPerson25;

  public void setNewPerson25(String p)
  { 
    newPerson25 = p;
  }

  public String getNewPerson25()
  { 
    return newPerson25;
  }

  public void selectPerson25(ValueChangeEvent event)
  { 
    log.info("selectPerson25" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person25 = em.find(Person.class, id);
      setPerson9(person25);
    }
  }

  @DataModel("person25List") private Map<String, String> person25List;

  public Map<String, String> getPerson25List()
  { 
    return person25List;
  }

  @Factory("person25List") public void initPerson25List()
  { 
    log.info("initPerson25List");
    person25List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person25List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchGroup2;

  public void setNewResearchGroup2(String p)
  { 
    newResearchGroup2 = p;
  }

  public String getNewResearchGroup2()
  { 
    return newResearchGroup2;
  }

  public void selectResearchGroup2(ValueChangeEvent event)
  { 
    log.info("selectResearchGroup2" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchGroup researchGroup2 = em.find(ResearchGroup.class, id);
      setResearchGroup0(researchGroup2);
    }
  }

  @DataModel("researchGroup2List") private Map<String, String> researchGroup2List;

  public Map<String, String> getResearchGroup2List()
  { 
    return researchGroup2List;
  }

  @Factory("researchGroup2List") public void initResearchGroup2List()
  { 
    log.info("initResearchGroup2List");
    researchGroup2List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchGroup").getResultList())
    { 
      ResearchGroup p = (ResearchGroup)o;
      researchGroup2List.put(p.getName(), p.getId().toString());
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
      addResearchProject1(researchProject6);
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

  private String newPresentation5;

  public void setNewPresentation5(String p)
  { 
    newPresentation5 = p;
  }

  public String getNewPresentation5()
  { 
    return newPresentation5;
  }

  public void selectPresentation5(ValueChangeEvent event)
  { 
    log.info("selectPresentation5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Presentation presentation5 = em.find(Presentation.class, id);
      addPresentation0(presentation5);
    }
  }

  @DataModel("presentation5List") private Map<String, String> presentation5List;

  public Map<String, String> getPresentation5List()
  { 
    return presentation5List;
  }

  @Factory("presentation5List") public void initPresentation5List()
  { 
    log.info("initPresentation5List");
    presentation5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Presentation").getResultList())
    { 
      Presentation p = (Presentation)o;
      presentation5List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1029List") private List<Person> person1029List;

  public List<Person> getPerson1029List()
  { 
    log.info("getPerson1029List");
    return person1029List;
  }

  @Factory("person1029List") public void initPerson1029List()
  { 
    log.info("initPerson1029List");
    person1029List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1129List") private List<ResearchProject> project1129List;

  public List<ResearchProject> getProject1129List()
  { 
    log.info("getProject1129List");
    return project1129List;
  }

  @Factory("project1129List") public void initProject1129List()
  { 
    log.info("initProject1129List");
    project1129List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}