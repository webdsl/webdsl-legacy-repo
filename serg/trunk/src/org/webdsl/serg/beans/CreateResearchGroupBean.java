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

@Stateful @Name("createResearchGroup") public class CreateResearchGroupBean  implements CreateResearchGroupBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createResearchGroup" + ".initalize()");
    ResearchGroup var68 = new ResearchGroup();
    researchGroup = var68;
    initPerson207List();
    initResearchProject55List();
    initColloquium10List();
    initNews9List();
    initPerson97List();
    initProject86List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson15(Person person206)
  { 
    this.getResearchGroup().getMembers().remove(person206);
  }

  public void addPerson15(Person person206)
  { 
    this.getResearchGroup().getMembers().add(person206);
  }

  public void removeResearchProject15(ResearchProject researchProject54)
  { 
    this.getResearchGroup().getProjects().remove(researchProject54);
  }

  public void addResearchProject15(ResearchProject researchProject54)
  { 
    this.getResearchGroup().getProjects().add(researchProject54);
  }

  public void removeColloquium1(Colloquium colloquium9)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium9);
  }

  public void addColloquium1(Colloquium colloquium9)
  { 
    this.getResearchGroup().getColloquia().add(colloquium9);
  }

  public void removeNews1(News news8)
  { 
    this.getResearchGroup().getNews().remove(news8);
  }

  public void addNews1(News news8)
  { 
    this.getResearchGroup().getNews().add(news8);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getResearchGroup());
    return "/" + "viewResearchGroup" + ".seam?" + ("group" + "=" + researchGroup.getId() + "");
  }

  private String newPerson207;

  public void setNewPerson207(String p)
  { 
    newPerson207 = p;
  }

  public String getNewPerson207()
  { 
    return newPerson207;
  }

  public void selectPerson207(ValueChangeEvent event)
  { 
    log.info("selectPerson207" + ": new value = " + " " + event.getNewValue());
    Person person207 = (Person)event.getNewValue();
  }

  @DataModel("person207List") private Map<String, String> person207List;

  public Map<String, String> getPerson207List()
  { 
    return person207List;
  }

  @Factory("person207List") public void initPerson207List()
  { 
    log.info("initPerson207List");
    person207List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person207List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject55;

  public void setNewResearchProject55(String p)
  { 
    newResearchProject55 = p;
  }

  public String getNewResearchProject55()
  { 
    return newResearchProject55;
  }

  public void selectResearchProject55(ValueChangeEvent event)
  { 
    log.info("selectResearchProject55" + ": new value = " + " " + event.getNewValue());
    ResearchProject researchProject55 = (ResearchProject)event.getNewValue();
  }

  @DataModel("researchProject55List") private Map<String, String> researchProject55List;

  public Map<String, String> getResearchProject55List()
  { 
    return researchProject55List;
  }

  @Factory("researchProject55List") public void initResearchProject55List()
  { 
    log.info("initResearchProject55List");
    researchProject55List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject55List.put(p.getName(), p.getId().toString());
    }
  }

  private String newColloquium10;

  public void setNewColloquium10(String p)
  { 
    newColloquium10 = p;
  }

  public String getNewColloquium10()
  { 
    return newColloquium10;
  }

  public void selectColloquium10(ValueChangeEvent event)
  { 
    log.info("selectColloquium10" + ": new value = " + " " + event.getNewValue());
    Colloquium colloquium10 = (Colloquium)event.getNewValue();
  }

  @DataModel("colloquium10List") private Map<String, String> colloquium10List;

  public Map<String, String> getColloquium10List()
  { 
    return colloquium10List;
  }

  @Factory("colloquium10List") public void initColloquium10List()
  { 
    log.info("initColloquium10List");
    colloquium10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Colloquium").getResultList())
    { 
      Colloquium p = (Colloquium)o;
      colloquium10List.put(p.getName(), p.getId().toString());
    }
  }

  private String newNews9;

  public void setNewNews9(String p)
  { 
    newNews9 = p;
  }

  public String getNewNews9()
  { 
    return newNews9;
  }

  public void selectNews9(ValueChangeEvent event)
  { 
    log.info("selectNews9" + ": new value = " + " " + event.getNewValue());
    News news9 = (News)event.getNewValue();
  }

  @DataModel("news9List") private Map<String, String> news9List;

  public Map<String, String> getNews9List()
  { 
    return news9List;
  }

  @Factory("news9List") public void initNews9List()
  { 
    log.info("initNews9List");
    news9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "News").getResultList())
    { 
      News p = (News)o;
      news9List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person97List") private List<Person> person97List;

  public List<Person> getPerson97List()
  { 
    log.info("getPerson97List");
    return person97List;
  }

  @Factory("person97List") public void initPerson97List()
  { 
    log.info("initPerson97List");
    person97List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project86List") private List<ResearchProject> project86List;

  public List<ResearchProject> getProject86List()
  { 
    log.info("getProject86List");
    return project86List;
  }

  @Factory("project86List") public void initProject86List()
  { 
    log.info("initProject86List");
    project86List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private ResearchGroup researchGroup;

  public ResearchGroup getResearchGroup()
  { 
    log.info("getResearchGroup");
    return researchGroup;
  }

  public void setResearchGroup(ResearchGroup researchGroup)
  { 
    log.info("setResearchGroup");
    this.researchGroup = researchGroup;
  }
}