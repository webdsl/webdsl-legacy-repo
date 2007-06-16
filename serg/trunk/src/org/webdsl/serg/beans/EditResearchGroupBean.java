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

@Stateful @Name("editResearchGroup") public class EditResearchGroupBean  implements EditResearchGroupBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editResearchGroup" + ".initalize()");
    if(researchGroupId == null)
    { 
      log.info("No " + "researchGroupId" + " defined, creating new " + "ResearchGroup");
      researchGroup = new ResearchGroup();
    }
    else
    { 
      researchGroup = em.find(ResearchGroup.class, researchGroupId);
    }
    initPerson195List();
    initResearchProject46List();
    initColloquium8List();
    initNews7List();
    initPerson91List();
    initProject81List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("researchGroup") private Long researchGroupId;

  private ResearchGroup researchGroup;

  public void setResearchGroup(ResearchGroup researchGroup)
  { 
    log.info("setResearchGroup");
    this.researchGroup = researchGroup;
  }

  public ResearchGroup getResearchGroup()
  { 
    log.info("getResearchGroup");
    return researchGroup;
  }

  public void removePerson12(Person person194)
  { 
    this.getResearchGroup().getMembers().remove(person194);
  }

  public void addPerson12(Person person194)
  { 
    this.getResearchGroup().getMembers().add(person194);
  }

  public void removeResearchProject12(ResearchProject researchProject45)
  { 
    this.getResearchGroup().getProjects().remove(researchProject45);
  }

  public void addResearchProject12(ResearchProject researchProject45)
  { 
    this.getResearchGroup().getProjects().add(researchProject45);
  }

  public void removeColloquium0(Colloquium colloquium7)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium7);
  }

  public void addColloquium0(Colloquium colloquium7)
  { 
    this.getResearchGroup().getColloquia().add(colloquium7);
  }

  public void removeNews0(News news6)
  { 
    this.getResearchGroup().getNews().remove(news6);
  }

  public void addNews0(News news6)
  { 
    this.getResearchGroup().getNews().add(news6);
  }

  @End public String cancel()
  { 
    return "/" + "viewResearchGroup" + ".seam?" + ("group" + "=" + researchGroup.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getResearchGroup());
    return "/" + "viewResearchGroup" + ".seam?" + ("group" + "=" + researchGroup.getId() + "");
  }

  private String newPerson195;

  public void setNewPerson195(String p)
  { 
    newPerson195 = p;
  }

  public String getNewPerson195()
  { 
    return newPerson195;
  }

  public void selectPerson195(ValueChangeEvent event)
  { 
    log.info("selectPerson195" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person195 = em.find(Person.class, id);
      addPerson12(person195);
    }
  }

  @DataModel("person195List") private Map<String, String> person195List;

  public Map<String, String> getPerson195List()
  { 
    return person195List;
  }

  @Factory("person195List") public void initPerson195List()
  { 
    log.info("initPerson195List");
    person195List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person195List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject46;

  public void setNewResearchProject46(String p)
  { 
    newResearchProject46 = p;
  }

  public String getNewResearchProject46()
  { 
    return newResearchProject46;
  }

  public void selectResearchProject46(ValueChangeEvent event)
  { 
    log.info("selectResearchProject46" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject46 = em.find(ResearchProject.class, id);
      addResearchProject12(researchProject46);
    }
  }

  @DataModel("researchProject46List") private Map<String, String> researchProject46List;

  public Map<String, String> getResearchProject46List()
  { 
    return researchProject46List;
  }

  @Factory("researchProject46List") public void initResearchProject46List()
  { 
    log.info("initResearchProject46List");
    researchProject46List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject46List.put(p.getName(), p.getId().toString());
    }
  }

  private String newColloquium8;

  public void setNewColloquium8(String p)
  { 
    newColloquium8 = p;
  }

  public String getNewColloquium8()
  { 
    return newColloquium8;
  }

  public void selectColloquium8(ValueChangeEvent event)
  { 
    log.info("selectColloquium8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Colloquium colloquium8 = em.find(Colloquium.class, id);
      addColloquium0(colloquium8);
    }
  }

  @DataModel("colloquium8List") private Map<String, String> colloquium8List;

  public Map<String, String> getColloquium8List()
  { 
    return colloquium8List;
  }

  @Factory("colloquium8List") public void initColloquium8List()
  { 
    log.info("initColloquium8List");
    colloquium8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Colloquium").getResultList())
    { 
      Colloquium p = (Colloquium)o;
      colloquium8List.put(p.getName(), p.getId().toString());
    }
  }

  private String newNews7;

  public void setNewNews7(String p)
  { 
    newNews7 = p;
  }

  public String getNewNews7()
  { 
    return newNews7;
  }

  public void selectNews7(ValueChangeEvent event)
  { 
    log.info("selectNews7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      News news7 = em.find(News.class, id);
      addNews0(news7);
    }
  }

  @DataModel("news7List") private Map<String, String> news7List;

  public Map<String, String> getNews7List()
  { 
    return news7List;
  }

  @Factory("news7List") public void initNews7List()
  { 
    log.info("initNews7List");
    news7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "News").getResultList())
    { 
      News p = (News)o;
      news7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person91List") private List<Person> person91List;

  public List<Person> getPerson91List()
  { 
    log.info("getPerson91List");
    return person91List;
  }

  @Factory("person91List") public void initPerson91List()
  { 
    log.info("initPerson91List");
    person91List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project81List") private List<ResearchProject> project81List;

  public List<ResearchProject> getProject81List()
  { 
    log.info("getProject81List");
    return project81List;
  }

  @Factory("project81List") public void initProject81List()
  { 
    log.info("initProject81List");
    project81List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}