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
    initPerson51List();
    initResearchProject12List();
    initNews4List();
    initPerson1042List();
    initProject1142List();
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

  public void removePerson6(Person person50)
  { 
    this.getResearchGroup().getMembers().remove(person50);
  }

  public void addPerson6(Person person50)
  { 
    this.getResearchGroup().getMembers().add(person50);
  }

  public void removeResearchProject3(ResearchProject researchProject11)
  { 
    this.getResearchGroup().getProjects().remove(researchProject11);
  }

  public void addResearchProject3(ResearchProject researchProject11)
  { 
    this.getResearchGroup().getProjects().add(researchProject11);
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

  private String newPerson51;

  public void setNewPerson51(String p)
  { 
    newPerson51 = p;
  }

  public String getNewPerson51()
  { 
    return newPerson51;
  }

  public void selectPerson51(ValueChangeEvent event)
  { 
    log.info("selectPerson51" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person51 = em.find(Person.class, id);
      addPerson6(person51);
    }
  }

  @DataModel("person51List") private Map<String, String> person51List;

  public Map<String, String> getPerson51List()
  { 
    return person51List;
  }

  @Factory("person51List") public void initPerson51List()
  { 
    log.info("initPerson51List");
    person51List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person51List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject12;

  public void setNewResearchProject12(String p)
  { 
    newResearchProject12 = p;
  }

  public String getNewResearchProject12()
  { 
    return newResearchProject12;
  }

  public void selectResearchProject12(ValueChangeEvent event)
  { 
    log.info("selectResearchProject12" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject12 = em.find(ResearchProject.class, id);
      addResearchProject3(researchProject12);
    }
  }

  @DataModel("researchProject12List") private Map<String, String> researchProject12List;

  public Map<String, String> getResearchProject12List()
  { 
    return researchProject12List;
  }

  @Factory("researchProject12List") public void initResearchProject12List()
  { 
    log.info("initResearchProject12List");
    researchProject12List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject12List.put(p.getName(), p.getId().toString());
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

  @DataModel("person1042List") private List<Person> person1042List;

  public List<Person> getPerson1042List()
  { 
    log.info("getPerson1042List");
    return person1042List;
  }

  @Factory("person1042List") public void initPerson1042List()
  { 
    log.info("initPerson1042List");
    person1042List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1142List") private List<ResearchProject> project1142List;

  public List<ResearchProject> getProject1142List()
  { 
    log.info("getProject1142List");
    return project1142List;
  }

  @Factory("project1142List") public void initProject1142List()
  { 
    log.info("initProject1142List");
    project1142List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}