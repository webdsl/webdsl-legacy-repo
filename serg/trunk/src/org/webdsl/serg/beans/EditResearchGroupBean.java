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
    initPerson183List();
    initResearchProject40List();
    initColloquium6List();
    initNews6List();
    initPerson91List();
    initProject80List();
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

  public void removePerson12(Person person182)
  { 
    this.getResearchGroup().getMembers().remove(person182);
  }

  public void addPerson12(Person person182)
  { 
    this.getResearchGroup().getMembers().add(person182);
  }

  public void removeResearchProject12(ResearchProject researchProject39)
  { 
    this.getResearchGroup().getProjects().remove(researchProject39);
  }

  public void addResearchProject12(ResearchProject researchProject39)
  { 
    this.getResearchGroup().getProjects().add(researchProject39);
  }

  public void removeColloquium0(Colloquium colloquium5)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium5);
  }

  public void addColloquium0(Colloquium colloquium5)
  { 
    this.getResearchGroup().getColloquia().add(colloquium5);
  }

  public void removeNews0(News news5)
  { 
    this.getResearchGroup().getNews().remove(news5);
  }

  public void addNews0(News news5)
  { 
    this.getResearchGroup().getNews().add(news5);
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

  private String newPerson183;

  public void setNewPerson183(String p)
  { 
    newPerson183 = p;
  }

  public String getNewPerson183()
  { 
    return newPerson183;
  }

  public void selectPerson183(ValueChangeEvent event)
  { 
    log.info("selectPerson183" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person183 = em.find(Person.class, id);
      addPerson12(person183);
    }
  }

  @DataModel("person183List") private Map<String, String> person183List;

  public Map<String, String> getPerson183List()
  { 
    return person183List;
  }

  @Factory("person183List") public void initPerson183List()
  { 
    log.info("initPerson183List");
    person183List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person183List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject40;

  public void setNewResearchProject40(String p)
  { 
    newResearchProject40 = p;
  }

  public String getNewResearchProject40()
  { 
    return newResearchProject40;
  }

  public void selectResearchProject40(ValueChangeEvent event)
  { 
    log.info("selectResearchProject40" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject40 = em.find(ResearchProject.class, id);
      addResearchProject12(researchProject40);
    }
  }

  @DataModel("researchProject40List") private Map<String, String> researchProject40List;

  public Map<String, String> getResearchProject40List()
  { 
    return researchProject40List;
  }

  @Factory("researchProject40List") public void initResearchProject40List()
  { 
    log.info("initResearchProject40List");
    researchProject40List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject40List.put(p.getName(), p.getId().toString());
    }
  }

  private String newColloquium6;

  public void setNewColloquium6(String p)
  { 
    newColloquium6 = p;
  }

  public String getNewColloquium6()
  { 
    return newColloquium6;
  }

  public void selectColloquium6(ValueChangeEvent event)
  { 
    log.info("selectColloquium6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Colloquium colloquium6 = em.find(Colloquium.class, id);
      addColloquium0(colloquium6);
    }
  }

  @DataModel("colloquium6List") private Map<String, String> colloquium6List;

  public Map<String, String> getColloquium6List()
  { 
    return colloquium6List;
  }

  @Factory("colloquium6List") public void initColloquium6List()
  { 
    log.info("initColloquium6List");
    colloquium6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Colloquium").getResultList())
    { 
      Colloquium p = (Colloquium)o;
      colloquium6List.put(p.getName(), p.getId().toString());
    }
  }

  private String newNews6;

  public void setNewNews6(String p)
  { 
    newNews6 = p;
  }

  public String getNewNews6()
  { 
    return newNews6;
  }

  public void selectNews6(ValueChangeEvent event)
  { 
    log.info("selectNews6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      News news6 = em.find(News.class, id);
      addNews0(news6);
    }
  }

  @DataModel("news6List") private Map<String, String> news6List;

  public Map<String, String> getNews6List()
  { 
    return news6List;
  }

  @Factory("news6List") public void initNews6List()
  { 
    log.info("initNews6List");
    news6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "News").getResultList())
    { 
      News p = (News)o;
      news6List.put(p.getName(), p.getId().toString());
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

  @DataModel("project80List") private List<ResearchProject> project80List;

  public List<ResearchProject> getProject80List()
  { 
    log.info("getProject80List");
    return project80List;
  }

  @Factory("project80List") public void initProject80List()
  { 
    log.info("initProject80List");
    project80List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}