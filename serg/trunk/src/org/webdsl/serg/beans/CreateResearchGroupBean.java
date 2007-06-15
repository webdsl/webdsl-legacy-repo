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
    ResearchGroup var50 = new ResearchGroup();
    researchGroup = var50;
    initPerson181List();
    initResearchProject37List();
    initColloquium8List();
    initNews8List();
    initPerson87List();
    initProject82List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson13(Person person180)
  { 
    this.getResearchGroup().getMembers().remove(person180);
  }

  public void addPerson13(Person person180)
  { 
    this.getResearchGroup().getMembers().add(person180);
  }

  public void removeResearchProject13(ResearchProject researchProject36)
  { 
    this.getResearchGroup().getProjects().remove(researchProject36);
  }

  public void addResearchProject13(ResearchProject researchProject36)
  { 
    this.getResearchGroup().getProjects().add(researchProject36);
  }

  public void removeColloquium1(Colloquium colloquium7)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium7);
  }

  public void addColloquium1(Colloquium colloquium7)
  { 
    this.getResearchGroup().getColloquia().add(colloquium7);
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
      addPerson13(person181);
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

  private String newResearchProject37;

  public void setNewResearchProject37(String p)
  { 
    newResearchProject37 = p;
  }

  public String getNewResearchProject37()
  { 
    return newResearchProject37;
  }

  public void selectResearchProject37(ValueChangeEvent event)
  { 
    log.info("selectResearchProject37" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject37 = em.find(ResearchProject.class, id);
      addResearchProject13(researchProject37);
    }
  }

  @DataModel("researchProject37List") private Map<String, String> researchProject37List;

  public Map<String, String> getResearchProject37List()
  { 
    return researchProject37List;
  }

  @Factory("researchProject37List") public void initResearchProject37List()
  { 
    log.info("initResearchProject37List");
    researchProject37List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject37List.put(p.getName(), p.getId().toString());
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
      addColloquium1(colloquium8);
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

  @DataModel("person87List") private List<Person> person87List;

  public List<Person> getPerson87List()
  { 
    log.info("getPerson87List");
    return person87List;
  }

  @Factory("person87List") public void initPerson87List()
  { 
    log.info("initPerson87List");
    person87List = em.createQuery("from " + "Person").getResultList();
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