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
    initPerson144List();
    initResearchGroup4List();
    initResearchProject11List();
    initPresentation7List();
    initPerson50List();
    initProject45List();
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

  public void setPerson6(Person person145)
  { 
    colloquium.setContact(person145);
  }

  public void setResearchGroup0(ResearchGroup researchGroup5)
  { 
    colloquium.setGroup(researchGroup5);
  }

  public void removeResearchProject0(ResearchProject researchProject10)
  { 
    this.getColloquium().getProjects().remove(researchProject10);
  }

  public void addResearchProject0(ResearchProject researchProject10)
  { 
    this.getColloquium().getProjects().add(researchProject10);
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

  private String newPerson144;

  public void setNewPerson144(String p)
  { 
    newPerson144 = p;
  }

  public String getNewPerson144()
  { 
    return newPerson144;
  }

  public void selectPerson144(ValueChangeEvent event)
  { 
    log.info("selectPerson144" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person144 = em.find(Person.class, id);
      setPerson6(person144);
    }
  }

  @DataModel("person144List") private Map<String, String> person144List;

  public Map<String, String> getPerson144List()
  { 
    return person144List;
  }

  @Factory("person144List") public void initPerson144List()
  { 
    log.info("initPerson144List");
    person144List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person144List.put(p.getName(), p.getId().toString());
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

  private String newResearchProject11;

  public void setNewResearchProject11(String p)
  { 
    newResearchProject11 = p;
  }

  public String getNewResearchProject11()
  { 
    return newResearchProject11;
  }

  public void selectResearchProject11(ValueChangeEvent event)
  { 
    log.info("selectResearchProject11" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject11 = em.find(ResearchProject.class, id);
      addResearchProject0(researchProject11);
    }
  }

  @DataModel("researchProject11List") private Map<String, String> researchProject11List;

  public Map<String, String> getResearchProject11List()
  { 
    return researchProject11List;
  }

  @Factory("researchProject11List") public void initResearchProject11List()
  { 
    log.info("initResearchProject11List");
    researchProject11List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject11List.put(p.getName(), p.getId().toString());
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
}