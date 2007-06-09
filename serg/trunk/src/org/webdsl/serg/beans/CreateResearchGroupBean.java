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
    ResearchGroup var32 = new ResearchGroup();
    researchGroup = var32;
    initPerson54List();
    initResearchProject29List();
    initColloquium5List();
    initNews6List();
    initPerson1044List();
    initProject1144List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson7(Person person53)
  { 
    this.getResearchGroup().getMembers().remove(person53);
  }

  public void addPerson7(Person person53)
  { 
    this.getResearchGroup().getMembers().add(person53);
  }

  public void removeResearchProject10(ResearchProject researchProject28)
  { 
    this.getResearchGroup().getProjects().remove(researchProject28);
  }

  public void addResearchProject10(ResearchProject researchProject28)
  { 
    this.getResearchGroup().getProjects().add(researchProject28);
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

  private String newPerson54;

  public void setNewPerson54(String p)
  { 
    newPerson54 = p;
  }

  public String getNewPerson54()
  { 
    return newPerson54;
  }

  public void selectPerson54(ValueChangeEvent event)
  { 
    log.info("selectPerson54" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person54 = em.find(Person.class, id);
      addPerson7(person54);
    }
  }

  @DataModel("person54List") private Map<String, String> person54List;

  public Map<String, String> getPerson54List()
  { 
    return person54List;
  }

  @Factory("person54List") public void initPerson54List()
  { 
    log.info("initPerson54List");
    person54List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person54List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject29;

  public void setNewResearchProject29(String p)
  { 
    newResearchProject29 = p;
  }

  public String getNewResearchProject29()
  { 
    return newResearchProject29;
  }

  public void selectResearchProject29(ValueChangeEvent event)
  { 
    log.info("selectResearchProject29" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject29 = em.find(ResearchProject.class, id);
      addResearchProject10(researchProject29);
    }
  }

  @DataModel("researchProject29List") private Map<String, String> researchProject29List;

  public Map<String, String> getResearchProject29List()
  { 
    return researchProject29List;
  }

  @Factory("researchProject29List") public void initResearchProject29List()
  { 
    log.info("initResearchProject29List");
    researchProject29List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject29List.put(p.getName(), p.getId().toString());
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