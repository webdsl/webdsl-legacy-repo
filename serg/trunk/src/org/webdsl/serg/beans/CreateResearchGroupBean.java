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
    ResearchGroup var36 = new ResearchGroup();
    researchGroup = var36;
    initPerson65List();
    initResearchProject34List();
    initColloquium5List();
    initNews6List();
    initPerson1044List();
    initProject1144List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson9(Person person64)
  { 
    this.getResearchGroup().getMembers().remove(person64);
  }

  public void addPerson9(Person person64)
  { 
    this.getResearchGroup().getMembers().add(person64);
  }

  public void removeResearchProject12(ResearchProject researchProject33)
  { 
    this.getResearchGroup().getProjects().remove(researchProject33);
  }

  public void addResearchProject12(ResearchProject researchProject33)
  { 
    this.getResearchGroup().getProjects().add(researchProject33);
  }

  public void removeColloquium1(Colloquium colloquium4)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium4);
  }

  public void addColloquium1(Colloquium colloquium4)
  { 
    this.getResearchGroup().getColloquia().add(colloquium4);
  }

  public void removeNews1(News news5)
  { 
    this.getResearchGroup().getNews().remove(news5);
  }

  public void addNews1(News news5)
  { 
    this.getResearchGroup().getNews().add(news5);
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

  private String newPerson65;

  public void setNewPerson65(String p)
  { 
    newPerson65 = p;
  }

  public String getNewPerson65()
  { 
    return newPerson65;
  }

  public void selectPerson65(ValueChangeEvent event)
  { 
    log.info("selectPerson65" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person65 = em.find(Person.class, id);
      addPerson9(person65);
    }
  }

  @DataModel("person65List") private Map<String, String> person65List;

  public Map<String, String> getPerson65List()
  { 
    return person65List;
  }

  @Factory("person65List") public void initPerson65List()
  { 
    log.info("initPerson65List");
    person65List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person65List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject34;

  public void setNewResearchProject34(String p)
  { 
    newResearchProject34 = p;
  }

  public String getNewResearchProject34()
  { 
    return newResearchProject34;
  }

  public void selectResearchProject34(ValueChangeEvent event)
  { 
    log.info("selectResearchProject34" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject34 = em.find(ResearchProject.class, id);
      addResearchProject12(researchProject34);
    }
  }

  @DataModel("researchProject34List") private Map<String, String> researchProject34List;

  public Map<String, String> getResearchProject34List()
  { 
    return researchProject34List;
  }

  @Factory("researchProject34List") public void initResearchProject34List()
  { 
    log.info("initResearchProject34List");
    researchProject34List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject34List.put(p.getName(), p.getId().toString());
    }
  }

  private String newColloquium5;

  public void setNewColloquium5(String p)
  { 
    newColloquium5 = p;
  }

  public String getNewColloquium5()
  { 
    return newColloquium5;
  }

  public void selectColloquium5(ValueChangeEvent event)
  { 
    log.info("selectColloquium5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Colloquium colloquium5 = em.find(Colloquium.class, id);
      addColloquium1(colloquium5);
    }
  }

  @DataModel("colloquium5List") private Map<String, String> colloquium5List;

  public Map<String, String> getColloquium5List()
  { 
    return colloquium5List;
  }

  @Factory("colloquium5List") public void initColloquium5List()
  { 
    log.info("initColloquium5List");
    colloquium5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Colloquium").getResultList())
    { 
      Colloquium p = (Colloquium)o;
      colloquium5List.put(p.getName(), p.getId().toString());
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
      addNews1(news6);
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

  @DataModel("person1044List") private List<Person> person1044List;

  public List<Person> getPerson1044List()
  { 
    log.info("getPerson1044List");
    return person1044List;
  }

  @Factory("person1044List") public void initPerson1044List()
  { 
    log.info("initPerson1044List");
    person1044List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1144List") private List<ResearchProject> project1144List;

  public List<ResearchProject> getProject1144List()
  { 
    log.info("getProject1144List");
    return project1144List;
  }

  @Factory("project1144List") public void initProject1144List()
  { 
    log.info("initProject1144List");
    project1144List = em.createQuery("from " + "ResearchProject").getResultList();
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