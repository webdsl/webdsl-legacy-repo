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
    ResearchGroup var65 = new ResearchGroup();
    researchGroup = var65;
    initPerson197List();
    initResearchProject48List();
    initColloquium10List();
    initNews9List();
    initPerson92List();
    initProject82List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson13(Person person196)
  { 
    this.getResearchGroup().getMembers().remove(person196);
  }

  public void addPerson13(Person person196)
  { 
    this.getResearchGroup().getMembers().add(person196);
  }

  public void removeResearchProject13(ResearchProject researchProject47)
  { 
    this.getResearchGroup().getProjects().remove(researchProject47);
  }

  public void addResearchProject13(ResearchProject researchProject47)
  { 
    this.getResearchGroup().getProjects().add(researchProject47);
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

  private String newPerson197;

  public void setNewPerson197(String p)
  { 
    newPerson197 = p;
  }

  public String getNewPerson197()
  { 
    return newPerson197;
  }

  public void selectPerson197(ValueChangeEvent event)
  { 
    log.info("selectPerson197" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person197 = em.find(Person.class, id);
      addPerson13(person197);
    }
  }

  @DataModel("person197List") private Map<String, String> person197List;

  public Map<String, String> getPerson197List()
  { 
    return person197List;
  }

  @Factory("person197List") public void initPerson197List()
  { 
    log.info("initPerson197List");
    person197List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person197List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject48;

  public void setNewResearchProject48(String p)
  { 
    newResearchProject48 = p;
  }

  public String getNewResearchProject48()
  { 
    return newResearchProject48;
  }

  public void selectResearchProject48(ValueChangeEvent event)
  { 
    log.info("selectResearchProject48" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject48 = em.find(ResearchProject.class, id);
      addResearchProject13(researchProject48);
    }
  }

  @DataModel("researchProject48List") private Map<String, String> researchProject48List;

  public Map<String, String> getResearchProject48List()
  { 
    return researchProject48List;
  }

  @Factory("researchProject48List") public void initResearchProject48List()
  { 
    log.info("initResearchProject48List");
    researchProject48List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject48List.put(p.getName(), p.getId().toString());
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

  @DataModel("person92List") private List<Person> person92List;

  public List<Person> getPerson92List()
  { 
    log.info("getPerson92List");
    return person92List;
  }

  @Factory("person92List") public void initPerson92List()
  { 
    log.info("initPerson92List");
    person92List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project82List") private List<ResearchProject> project82List;

  public List<ResearchProject> getProject82List()
  { 
    log.info("getProject82List");
    return project82List;
  }

  @Factory("project82List") public void initProject82List()
  { 
    log.info("initProject82List");
    project82List = em.createQuery("from " + "ResearchProject").getResultList();
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