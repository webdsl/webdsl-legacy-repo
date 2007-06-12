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
    initResearchProject44List();
    initColloquium5List();
    initNews6List();
    initPerson84List();
    initProject79List();
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

  public void removePerson15(Person person178)
  { 
    this.getResearchGroup().getMembers().remove(person178);
  }

  public void addPerson15(Person person178)
  { 
    this.getResearchGroup().getMembers().add(person178);
  }

  public void removeResearchProject15(ResearchProject researchProject43)
  { 
    this.getResearchGroup().getProjects().remove(researchProject43);
  }

  public void addResearchProject15(ResearchProject researchProject43)
  { 
    this.getResearchGroup().getProjects().add(researchProject43);
  }

  public void removeColloquium0(Colloquium colloquium4)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium4);
  }

  public void addColloquium0(Colloquium colloquium4)
  { 
    this.getResearchGroup().getColloquia().add(colloquium4);
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
      addPerson15(person179);
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

  private String newResearchProject44;

  public void setNewResearchProject44(String p)
  { 
    newResearchProject44 = p;
  }

  public String getNewResearchProject44()
  { 
    return newResearchProject44;
  }

  public void selectResearchProject44(ValueChangeEvent event)
  { 
    log.info("selectResearchProject44" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject44 = em.find(ResearchProject.class, id);
      addResearchProject15(researchProject44);
    }
  }

  @DataModel("researchProject44List") private Map<String, String> researchProject44List;

  public Map<String, String> getResearchProject44List()
  { 
    return researchProject44List;
  }

  @Factory("researchProject44List") public void initResearchProject44List()
  { 
    log.info("initResearchProject44List");
    researchProject44List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject44List.put(p.getName(), p.getId().toString());
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
      addColloquium0(colloquium5);
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

  @DataModel("person84List") private List<Person> person84List;

  public List<Person> getPerson84List()
  { 
    log.info("getPerson84List");
    return person84List;
  }

  @Factory("person84List") public void initPerson84List()
  { 
    log.info("initPerson84List");
    person84List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project79List") private List<ResearchProject> project79List;

  public List<ResearchProject> getProject79List()
  { 
    log.info("getProject79List");
    return project79List;
  }

  @Factory("project79List") public void initProject79List()
  { 
    log.info("initProject79List");
    project79List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}