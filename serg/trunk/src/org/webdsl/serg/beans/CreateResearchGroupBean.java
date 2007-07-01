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
    initPerson222List();
    initResearchProject55List();
    initColloquium10List();
    initNews9List();
    initPerson99List();
    initProject86List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson15(Person person221)
  { 
    this.getResearchGroup().getMembers().remove(person221);
  }

  public void addPerson15(Person person221)
  { 
    this.getResearchGroup().getMembers().add(person221);
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

  private String newPerson222;

  public void setNewPerson222(String p)
  { 
    newPerson222 = p;
  }

  public String getNewPerson222()
  { 
    return newPerson222;
  }

  public void selectPerson222(ValueChangeEvent event)
  { 
    log.info("selectPerson222" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person222 = em.find(Person.class, id);
      addPerson15(person222);
    }
  }

  @DataModel("person222List") private Map<String, String> person222List;

  public Map<String, String> getPerson222List()
  { 
    return person222List;
  }

  @Factory("person222List") public void initPerson222List()
  { 
    log.info("initPerson222List");
    person222List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person222List.put(p.getName(), p.getId().toString());
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject55 = em.find(ResearchProject.class, id);
      addResearchProject15(researchProject55);
    }
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Colloquium colloquium10 = em.find(Colloquium.class, id);
      addColloquium1(colloquium10);
    }
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      News news9 = em.find(News.class, id);
      addNews1(news9);
    }
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

  @DataModel("person99List") private List<Person> person99List;

  public List<Person> getPerson99List()
  { 
    log.info("getPerson99List");
    return person99List;
  }

  @Factory("person99List") public void initPerson99List()
  { 
    log.info("initPerson99List");
    person99List = em.createQuery("from " + "Person").getResultList();
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