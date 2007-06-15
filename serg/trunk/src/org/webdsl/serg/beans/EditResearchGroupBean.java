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
    initPerson179List();
    initResearchProject35List();
    initColloquium6List();
    initNews6List();
    initPerson86List();
    initProject81List();
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

  public void removePerson12(Person person178)
  { 
    this.getResearchGroup().getMembers().remove(person178);
  }

  public void addPerson12(Person person178)
  { 
    this.getResearchGroup().getMembers().add(person178);
  }

  public void removeResearchProject12(ResearchProject researchProject34)
  { 
    this.getResearchGroup().getProjects().remove(researchProject34);
  }

  public void addResearchProject12(ResearchProject researchProject34)
  { 
    this.getResearchGroup().getProjects().add(researchProject34);
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

  private String newPerson179;

  public void setNewPerson179(String p)
  { 
    newPerson179 = p;
  }

  public String getNewPerson179()
  { 
    return newPerson179;
  }

  public void selectPerson179(ValueChangeEvent event)
  { 
    log.info("selectPerson179" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person179 = em.find(Person.class, id);
      addPerson12(person179);
    }
  }

  @DataModel("person179List") private Map<String, String> person179List;

  public Map<String, String> getPerson179List()
  { 
    return person179List;
  }

  @Factory("person179List") public void initPerson179List()
  { 
    log.info("initPerson179List");
    person179List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person179List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject35;

  public void setNewResearchProject35(String p)
  { 
    newResearchProject35 = p;
  }

  public String getNewResearchProject35()
  { 
    return newResearchProject35;
  }

  public void selectResearchProject35(ValueChangeEvent event)
  { 
    log.info("selectResearchProject35" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject35 = em.find(ResearchProject.class, id);
      addResearchProject12(researchProject35);
    }
  }

  @DataModel("researchProject35List") private Map<String, String> researchProject35List;

  public Map<String, String> getResearchProject35List()
  { 
    return researchProject35List;
  }

  @Factory("researchProject35List") public void initResearchProject35List()
  { 
    log.info("initResearchProject35List");
    researchProject35List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject35List.put(p.getName(), p.getId().toString());
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
}