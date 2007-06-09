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
    initPerson53List();
    initResearchProject14List();
    initNews6List();
    initPerson1043List();
    initProject1143List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson7(Person person52)
  { 
    this.getResearchGroup().getMembers().remove(person52);
  }

  public void addPerson7(Person person52)
  { 
    this.getResearchGroup().getMembers().add(person52);
  }

  public void removeResearchProject4(ResearchProject researchProject13)
  { 
    this.getResearchGroup().getProjects().remove(researchProject13);
  }

  public void addResearchProject4(ResearchProject researchProject13)
  { 
    this.getResearchGroup().getProjects().add(researchProject13);
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

  private String newPerson53;

  public void setNewPerson53(String p)
  { 
    newPerson53 = p;
  }

  public String getNewPerson53()
  { 
    return newPerson53;
  }

  public void selectPerson53(ValueChangeEvent event)
  { 
    log.info("selectPerson53" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person53 = em.find(Person.class, id);
      addPerson7(person53);
    }
  }

  @DataModel("person53List") private Map<String, String> person53List;

  public Map<String, String> getPerson53List()
  { 
    return person53List;
  }

  @Factory("person53List") public void initPerson53List()
  { 
    log.info("initPerson53List");
    person53List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person53List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject14;

  public void setNewResearchProject14(String p)
  { 
    newResearchProject14 = p;
  }

  public String getNewResearchProject14()
  { 
    return newResearchProject14;
  }

  public void selectResearchProject14(ValueChangeEvent event)
  { 
    log.info("selectResearchProject14" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject14 = em.find(ResearchProject.class, id);
      addResearchProject4(researchProject14);
    }
  }

  @DataModel("researchProject14List") private Map<String, String> researchProject14List;

  public Map<String, String> getResearchProject14List()
  { 
    return researchProject14List;
  }

  @Factory("researchProject14List") public void initResearchProject14List()
  { 
    log.info("initResearchProject14List");
    researchProject14List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject14List.put(p.getName(), p.getId().toString());
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

  @DataModel("person1043List") private List<Person> person1043List;

  public List<Person> getPerson1043List()
  { 
    log.info("getPerson1043List");
    return person1043List;
  }

  @Factory("person1043List") public void initPerson1043List()
  { 
    log.info("initPerson1043List");
    person1043List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1143List") private List<ResearchProject> project1143List;

  public List<ResearchProject> getProject1143List()
  { 
    log.info("getProject1143List");
    return project1143List;
  }

  @Factory("project1143List") public void initProject1143List()
  { 
    log.info("initProject1143List");
    project1143List = em.createQuery("from " + "ResearchProject").getResultList();
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