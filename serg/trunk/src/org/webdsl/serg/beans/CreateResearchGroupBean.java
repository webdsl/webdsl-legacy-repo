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
    ResearchGroup var60 = new ResearchGroup();
    researchGroup = var60;
    initPerson185List();
    initResearchProject42List();
    initColloquium8List();
    initNews8List();
    initPerson92List();
    initProject81List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson13(Person person184)
  { 
    this.getResearchGroup().getMembers().remove(person184);
  }

  public void addPerson13(Person person184)
  { 
    this.getResearchGroup().getMembers().add(person184);
  }

  public void removeResearchProject13(ResearchProject researchProject41)
  { 
    this.getResearchGroup().getProjects().remove(researchProject41);
  }

  public void addResearchProject13(ResearchProject researchProject41)
  { 
    this.getResearchGroup().getProjects().add(researchProject41);
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

  private String newPerson185;

  public void setNewPerson185(String p)
  { 
    newPerson185 = p;
  }

  public String getNewPerson185()
  { 
    return newPerson185;
  }

  public void selectPerson185(ValueChangeEvent event)
  { 
    log.info("selectPerson185" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person185 = em.find(Person.class, id);
      addPerson13(person185);
    }
  }

  @DataModel("person185List") private Map<String, String> person185List;

  public Map<String, String> getPerson185List()
  { 
    return person185List;
  }

  @Factory("person185List") public void initPerson185List()
  { 
    log.info("initPerson185List");
    person185List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person185List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject42;

  public void setNewResearchProject42(String p)
  { 
    newResearchProject42 = p;
  }

  public String getNewResearchProject42()
  { 
    return newResearchProject42;
  }

  public void selectResearchProject42(ValueChangeEvent event)
  { 
    log.info("selectResearchProject42" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject42 = em.find(ResearchProject.class, id);
      addResearchProject13(researchProject42);
    }
  }

  @DataModel("researchProject42List") private Map<String, String> researchProject42List;

  public Map<String, String> getResearchProject42List()
  { 
    return researchProject42List;
  }

  @Factory("researchProject42List") public void initResearchProject42List()
  { 
    log.info("initResearchProject42List");
    researchProject42List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject42List.put(p.getName(), p.getId().toString());
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