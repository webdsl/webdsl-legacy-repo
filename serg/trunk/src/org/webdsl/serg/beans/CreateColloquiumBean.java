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
    initPerson33List();
    initResearchGroup4List();
    initResearchProject8List();
    initPresentation7List();
    initPerson1030List();
    initProject1130List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson10(Person person34)
  { 
    colloquium.setContact(person34);
  }

  public void setResearchGroup1(ResearchGroup researchGroup5)
  { 
    colloquium.setGroup(researchGroup5);
  }

  public void removeResearchProject2(ResearchProject researchProject7)
  { 
    this.getColloquium().getProjects().remove(researchProject7);
  }

  public void addResearchProject2(ResearchProject researchProject7)
  { 
    this.getColloquium().getProjects().add(researchProject7);
  }

  public void removePresentation1(Presentation presentation6)
  { 
    this.getColloquium().getPresentations().remove(presentation6);
  }

  public void addPresentation1(Presentation presentation6)
  { 
    this.getColloquium().getPresentations().add(presentation6);
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

  private String newPerson33;

  public void setNewPerson33(String p)
  { 
    newPerson33 = p;
  }

  public String getNewPerson33()
  { 
    return newPerson33;
  }

  public void selectPerson33(ValueChangeEvent event)
  { 
    log.info("selectPerson33" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person33 = em.find(Person.class, id);
      setPerson10(person33);
    }
  }

  @DataModel("person33List") private Map<String, String> person33List;

  public Map<String, String> getPerson33List()
  { 
    return person33List;
  }

  @Factory("person33List") public void initPerson33List()
  { 
    log.info("initPerson33List");
    person33List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person33List.put(p.getName(), p.getId().toString());
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
      setResearchGroup1(researchGroup4);
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
      addResearchProject2(researchProject8);
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
      addPresentation1(presentation7);
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

  @DataModel("person1030List") private List<Person> person1030List;

  public List<Person> getPerson1030List()
  { 
    log.info("getPerson1030List");
    return person1030List;
  }

  @Factory("person1030List") public void initPerson1030List()
  { 
    log.info("initPerson1030List");
    person1030List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1130List") private List<ResearchProject> project1130List;

  public List<ResearchProject> getProject1130List()
  { 
    log.info("getProject1130List");
    return project1130List;
  }

  @Factory("project1130List") public void initProject1130List()
  { 
    log.info("initProject1130List");
    project1130List = em.createQuery("from " + "ResearchProject").getResultList();
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