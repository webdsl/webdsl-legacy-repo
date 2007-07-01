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
    initPerson220List();
    initResearchProject53List();
    initColloquium8List();
    initNews7List();
    initPerson98List();
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

  public void removePerson14(Person person219)
  { 
    this.getResearchGroup().getMembers().remove(person219);
  }

  public void addPerson14(Person person219)
  { 
    this.getResearchGroup().getMembers().add(person219);
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

  private String newPerson220;

  public void setNewPerson220(String p)
  { 
    newPerson220 = p;
  }

  public String getNewPerson220()
  { 
    return newPerson220;
  }

  public void selectPerson220(ValueChangeEvent event)
  { 
    log.info("selectPerson220" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person220 = em.find(Person.class, id);
      addPerson14(person220);
    }
  }

  @DataModel("person220List") private Map<String, String> person220List;

  public Map<String, String> getPerson220List()
  { 
    return person220List;
  }

  @Factory("person220List") public void initPerson220List()
  { 
    log.info("initPerson220List");
    person220List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person220List.put(p.getName(), p.getId().toString());
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject53 = em.find(ResearchProject.class, id);
      addResearchProject14(researchProject53);
    }
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Colloquium colloquium8 = em.find(Colloquium.class, id);
      addColloquium0(colloquium8);
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      News news7 = em.find(News.class, id);
      addNews0(news7);
    }
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

  @DataModel("person98List") private List<Person> person98List;

  public List<Person> getPerson98List()
  { 
    log.info("getPerson98List");
    return person98List;
  }

  @Factory("person98List") public void initPerson98List()
  { 
    log.info("initPerson98List");
    person98List = em.createQuery("from " + "Person").getResultList();
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