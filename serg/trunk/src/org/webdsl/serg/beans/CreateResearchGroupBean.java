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
    initPerson163List();
    initResearchProject37List();
    initColloquium7List();
    initNews8List();
    initPerson86List();
    initProject81List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson13(Person person162)
  { 
    this.getResearchGroup().getMembers().remove(person162);
  }

  public void addPerson13(Person person162)
  { 
    this.getResearchGroup().getMembers().add(person162);
  }

  public void removeResearchProject13(ResearchProject researchProject36)
  { 
    this.getResearchGroup().getProjects().remove(researchProject36);
  }

  public void addResearchProject13(ResearchProject researchProject36)
  { 
    this.getResearchGroup().getProjects().add(researchProject36);
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

  private String newPerson163;

  public void setNewPerson163(String p)
  { 
    newPerson163 = p;
  }

  public String getNewPerson163()
  { 
    return newPerson163;
  }

  public void selectPerson163(ValueChangeEvent event)
  { 
    log.info("selectPerson163" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person163 = em.find(Person.class, id);
      addPerson13(person163);
    }
  }

  @DataModel("person163List") private Map<String, String> person163List;

  public Map<String, String> getPerson163List()
  { 
    return person163List;
  }

  @Factory("person163List") public void initPerson163List()
  { 
    log.info("initPerson163List");
    person163List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person163List.put(p.getName(), p.getId().toString());
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

  @DataModel("person86List") private List<Person> person86List;

  public List<Person> getPerson86List()
  { 
    log.info("getPerson86List");
    return person86List;
  }

  @Factory("person86List") public void initPerson86List()
  { 
    log.info("initPerson86List");
    person86List = em.createQuery("from " + "Person").getResultList();
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