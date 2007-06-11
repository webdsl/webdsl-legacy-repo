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
    initPerson63List();
    initResearchProject32List();
    initColloquium3List();
    initNews4List();
    initPerson1043List();
    initProject1143List();
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

  public void removePerson8(Person person62)
  { 
    this.getResearchGroup().getMembers().remove(person62);
  }

  public void addPerson8(Person person62)
  { 
    this.getResearchGroup().getMembers().add(person62);
  }

  public void removeResearchProject11(ResearchProject researchProject31)
  { 
    this.getResearchGroup().getProjects().remove(researchProject31);
  }

  public void addResearchProject11(ResearchProject researchProject31)
  { 
    this.getResearchGroup().getProjects().add(researchProject31);
  }

  public void removeColloquium0(Colloquium colloquium2)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium2);
  }

  public void addColloquium0(Colloquium colloquium2)
  { 
    this.getResearchGroup().getColloquia().add(colloquium2);
  }

  public void removeNews0(News news3)
  { 
    this.getResearchGroup().getNews().remove(news3);
  }

  public void addNews0(News news3)
  { 
    this.getResearchGroup().getNews().add(news3);
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

  private String newPerson63;

  public void setNewPerson63(String p)
  { 
    newPerson63 = p;
  }

  public String getNewPerson63()
  { 
    return newPerson63;
  }

  public void selectPerson63(ValueChangeEvent event)
  { 
    log.info("selectPerson63" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person63 = em.find(Person.class, id);
      addPerson8(person63);
    }
  }

  @DataModel("person63List") private Map<String, String> person63List;

  public Map<String, String> getPerson63List()
  { 
    return person63List;
  }

  @Factory("person63List") public void initPerson63List()
  { 
    log.info("initPerson63List");
    person63List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person63List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject32;

  public void setNewResearchProject32(String p)
  { 
    newResearchProject32 = p;
  }

  public String getNewResearchProject32()
  { 
    return newResearchProject32;
  }

  public void selectResearchProject32(ValueChangeEvent event)
  { 
    log.info("selectResearchProject32" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject32 = em.find(ResearchProject.class, id);
      addResearchProject11(researchProject32);
    }
  }

  @DataModel("researchProject32List") private Map<String, String> researchProject32List;

  public Map<String, String> getResearchProject32List()
  { 
    return researchProject32List;
  }

  @Factory("researchProject32List") public void initResearchProject32List()
  { 
    log.info("initResearchProject32List");
    researchProject32List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject32List.put(p.getName(), p.getId().toString());
    }
  }

  private String newColloquium3;

  public void setNewColloquium3(String p)
  { 
    newColloquium3 = p;
  }

  public String getNewColloquium3()
  { 
    return newColloquium3;
  }

  public void selectColloquium3(ValueChangeEvent event)
  { 
    log.info("selectColloquium3" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Colloquium colloquium3 = em.find(Colloquium.class, id);
      addColloquium0(colloquium3);
    }
  }

  @DataModel("colloquium3List") private Map<String, String> colloquium3List;

  public Map<String, String> getColloquium3List()
  { 
    return colloquium3List;
  }

  @Factory("colloquium3List") public void initColloquium3List()
  { 
    log.info("initColloquium3List");
    colloquium3List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Colloquium").getResultList())
    { 
      Colloquium p = (Colloquium)o;
      colloquium3List.put(p.getName(), p.getId().toString());
    }
  }

  private String newNews4;

  public void setNewNews4(String p)
  { 
    newNews4 = p;
  }

  public String getNewNews4()
  { 
    return newNews4;
  }

  public void selectNews4(ValueChangeEvent event)
  { 
    log.info("selectNews4" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      News news4 = em.find(News.class, id);
      addNews0(news4);
    }
  }

  @DataModel("news4List") private Map<String, String> news4List;

  public Map<String, String> getNews4List()
  { 
    return news4List;
  }

  @Factory("news4List") public void initNews4List()
  { 
    log.info("initNews4List");
    news4List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "News").getResultList())
    { 
      News p = (News)o;
      news4List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1043List") private List<Person> person1043List;

  public List<Person> getPerson1043List()
  { 
    log.info("getPerson1043List");
    return person1043List;
  }

  @Factory("person1043List") public void initPerson1043List()
  { 
    log.info("initPerson1043List");
    person1043List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1143List") private List<ResearchProject> project1143List;

  public List<ResearchProject> getProject1143List()
  { 
    log.info("getProject1143List");
    return project1143List;
  }

  @Factory("project1143List") public void initProject1143List()
  { 
    log.info("initProject1143List");
    project1143List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}