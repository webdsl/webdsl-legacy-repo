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
    initPerson205List();
    initResearchProject53List();
    initColloquium8List();
    initNews7List();
    initPerson96List();
    initProject85List();
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

  public void removePerson14(Person person204)
  { 
    this.getResearchGroup().getMembers().remove(person204);
  }

  public void addPerson14(Person person204)
  { 
    this.getResearchGroup().getMembers().add(person204);
  }

  public void removeResearchProject14(ResearchProject researchProject52)
  { 
    this.getResearchGroup().getProjects().remove(researchProject52);
  }

  public void addResearchProject14(ResearchProject researchProject52)
  { 
    this.getResearchGroup().getProjects().add(researchProject52);
  }

  public void removeColloquium0(Colloquium colloquium7)
  { 
    this.getResearchGroup().getColloquia().remove(colloquium7);
  }

  public void addColloquium0(Colloquium colloquium7)
  { 
    this.getResearchGroup().getColloquia().add(colloquium7);
  }

  public void removeNews0(News news6)
  { 
    this.getResearchGroup().getNews().remove(news6);
  }

  public void addNews0(News news6)
  { 
    this.getResearchGroup().getNews().add(news6);
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

  private String newPerson205;

  public void setNewPerson205(String p)
  { 
    newPerson205 = p;
  }

  public String getNewPerson205()
  { 
    return newPerson205;
  }

  public void selectPerson205(ValueChangeEvent event)
  { 
    log.info("selectPerson205" + ": new value = " + " " + event.getNewValue());
    Person person205 = (Person)event.getNewValue();
  }

  @DataModel("person205List") private Map<String, String> person205List;

  public Map<String, String> getPerson205List()
  { 
    return person205List;
  }

  @Factory("person205List") public void initPerson205List()
  { 
    log.info("initPerson205List");
    person205List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person205List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject53;

  public void setNewResearchProject53(String p)
  { 
    newResearchProject53 = p;
  }

  public String getNewResearchProject53()
  { 
    return newResearchProject53;
  }

  public void selectResearchProject53(ValueChangeEvent event)
  { 
    log.info("selectResearchProject53" + ": new value = " + " " + event.getNewValue());
    ResearchProject researchProject53 = (ResearchProject)event.getNewValue();
  }

  @DataModel("researchProject53List") private Map<String, String> researchProject53List;

  public Map<String, String> getResearchProject53List()
  { 
    return researchProject53List;
  }

  @Factory("researchProject53List") public void initResearchProject53List()
  { 
    log.info("initResearchProject53List");
    researchProject53List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject53List.put(p.getName(), p.getId().toString());
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
    Colloquium colloquium8 = (Colloquium)event.getNewValue();
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

  private String newNews7;

  public void setNewNews7(String p)
  { 
    newNews7 = p;
  }

  public String getNewNews7()
  { 
    return newNews7;
  }

  public void selectNews7(ValueChangeEvent event)
  { 
    log.info("selectNews7" + ": new value = " + " " + event.getNewValue());
    News news7 = (News)event.getNewValue();
  }

  @DataModel("news7List") private Map<String, String> news7List;

  public Map<String, String> getNews7List()
  { 
    return news7List;
  }

  @Factory("news7List") public void initNews7List()
  { 
    log.info("initNews7List");
    news7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "News").getResultList())
    { 
      News p = (News)o;
      news7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person96List") private List<Person> person96List;

  public List<Person> getPerson96List()
  { 
    log.info("getPerson96List");
    return person96List;
  }

  @Factory("person96List") public void initPerson96List()
  { 
    log.info("initPerson96List");
    person96List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project85List") private List<ResearchProject> project85List;

  public List<ResearchProject> getProject85List()
  { 
    log.info("getProject85List");
    return project85List;
  }

  @Factory("project85List") public void initProject85List()
  { 
    log.info("initProject85List");
    project85List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}