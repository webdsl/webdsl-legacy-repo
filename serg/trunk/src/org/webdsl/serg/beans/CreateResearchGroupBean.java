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
    ResearchGroup var48 = new ResearchGroup();
    researchGroup = var48;
    initPerson181List();
    initResearchProject46List();
    initColloquium7List();
    initNews8List();
    initPerson85List();
    initProject80List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson16(Person person180)
  { 
    this.getResearchGroup().getMembers().remove(person180);
  }

  public void addPerson16(Person person180)
  { 
    this.getResearchGroup().getMembers().add(person180);
  }

  public void removeResearchProject16(ResearchProject researchProject45)
  { 
    this.getResearchGroup().getProjects().remove(researchProject45);
  }

  public void addResearchProject16(ResearchProject researchProject45)
  { 
    this.getResearchGroup().getProjects().add(researchProject45);
  }

  public void removeColloquium1(Colloquium colloquium6)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium6);
  }

  public void addColloquium1(Colloquium colloquium6)
  { 
    this.getResearchGroup().getColloquia().add(colloquium6);
  }

  public void removeNews1(News news7)
  { 
    this.getResearchGroup().getNews().remove(news7);
  }

  public void addNews1(News news7)
  { 
    this.getResearchGroup().getNews().add(news7);
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

  private String newPerson181;

  public void setNewPerson181(String p)
  { 
    newPerson181 = p;
  }

  public String getNewPerson181()
  { 
    return newPerson181;
  }

  public void selectPerson181(ValueChangeEvent event)
  { 
    log.info("selectPerson181" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person181 = em.find(Person.class, id);
      addPerson16(person181);
    }
  }

  @DataModel("person181List") private Map<String, String> person181List;

  public Map<String, String> getPerson181List()
  { 
    return person181List;
  }

  @Factory("person181List") public void initPerson181List()
  { 
    log.info("initPerson181List");
    person181List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person181List.put(p.getName(), p.getId().toString());
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
      addResearchProject16(researchProject46);
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

  private String newColloquium7;

  public void setNewColloquium7(String p)
  { 
    newColloquium7 = p;
  }

  public String getNewColloquium7()
  { 
    return newColloquium7;
  }

  public void selectColloquium7(ValueChangeEvent event)
  { 
    log.info("selectColloquium7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Colloquium colloquium7 = em.find(Colloquium.class, id);
      addColloquium1(colloquium7);
    }
  }

  @DataModel("colloquium7List") private Map<String, String> colloquium7List;

  public Map<String, String> getColloquium7List()
  { 
    return colloquium7List;
  }

  @Factory("colloquium7List") public void initColloquium7List()
  { 
    log.info("initColloquium7List");
    colloquium7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Colloquium").getResultList())
    { 
      Colloquium p = (Colloquium)o;
      colloquium7List.put(p.getName(), p.getId().toString());
    }
  }

  private String newNews8;

  public void setNewNews8(String p)
  { 
    newNews8 = p;
  }

  public String getNewNews8()
  { 
    return newNews8;
  }

  public void selectNews8(ValueChangeEvent event)
  { 
    log.info("selectNews8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      News news8 = em.find(News.class, id);
      addNews1(news8);
    }
  }

  @DataModel("news8List") private Map<String, String> news8List;

  public Map<String, String> getNews8List()
  { 
    return news8List;
  }

  @Factory("news8List") public void initNews8List()
  { 
    log.info("initNews8List");
    news8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "News").getResultList())
    { 
      News p = (News)o;
      news8List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person85List") private List<Person> person85List;

  public List<Person> getPerson85List()
  { 
    log.info("getPerson85List");
    return person85List;
  }

  @Factory("person85List") public void initPerson85List()
  { 
    log.info("initPerson85List");
    person85List = em.createQuery("from " + "Person").getResultList();
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