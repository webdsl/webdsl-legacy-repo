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

@Stateful @Name("editColloquium") public class EditColloquiumBean  implements EditColloquiumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editColloquium" + ".initalize()");
    if(colloquiumId == null)
    { 
      log.info("No " + "colloquiumId" + " defined, creating new " + "Colloquium");
      colloquium = new Colloquium();
    }
    else
    { 
      colloquium = em.find(Colloquium.class, colloquiumId);
    }
    initPresentation4List();
    initPerson24List();
    initPerson1027List();
    initProject1127List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("colloquium") private Long colloquiumId;

  private Colloquium colloquium;

  public void setColloquium(Colloquium colloquium)
  { 
    log.info("setColloquium");
    this.colloquium = colloquium;
  }

  public Colloquium getColloquium()
  { 
    log.info("getColloquium");
    return colloquium;
  }

  public void removePresentation0(Presentation presentation3)
  { 
    this.getColloquium().getTalks().remove(presentation3);
  }

  public void addPresentation0(Presentation presentation3)
  { 
    this.getColloquium().getTalks().add(presentation3);
  }

  public void setPerson9(Person person25)
  { 
    colloquium.setContact(person25);
  }

  @End public String cancel()
  { 
    return "/" + "viewColloquium" + ".seam?" + ("colloquium" + "=" + colloquium.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getColloquium());
    return "/" + "viewColloquium" + ".seam?" + ("colloquium" + "=" + colloquium.getId() + "");
  }

  private String newPresentation4;

  public void setNewPresentation4(String p)
  { 
    newPresentation4 = p;
  }

  public String getNewPresentation4()
  { 
    return newPresentation4;
  }

  public void selectPresentation4(ValueChangeEvent event)
  { 
    log.info("selectPresentation4" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Presentation presentation4 = em.find(Presentation.class, id);
      addPresentation0(presentation4);
    }
  }

  @DataModel("presentation4List") private Map<String, String> presentation4List;

  public Map<String, String> getPresentation4List()
  { 
    return presentation4List;
  }

  @Factory("presentation4List") public void initPresentation4List()
  { 
    log.info("initPresentation4List");
    presentation4List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Presentation").getResultList())
    { 
      Presentation p = (Presentation)o;
      presentation4List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson24;

  public void setNewPerson24(String p)
  { 
    newPerson24 = p;
  }

  public String getNewPerson24()
  { 
    return newPerson24;
  }

  public void selectPerson24(ValueChangeEvent event)
  { 
    log.info("selectPerson24" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person24 = em.find(Person.class, id);
      setPerson9(person24);
    }
  }

  @DataModel("person24List") private Map<String, String> person24List;

  public Map<String, String> getPerson24List()
  { 
    return person24List;
  }

  @Factory("person24List") public void initPerson24List()
  { 
    log.info("initPerson24List");
    person24List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person24List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1027List") private List<Person> person1027List;

  public List<Person> getPerson1027List()
  { 
    log.info("getPerson1027List");
    return person1027List;
  }

  @Factory("person1027List") public void initPerson1027List()
  { 
    log.info("initPerson1027List");
    person1027List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1127List") private List<ResearchProject> project1127List;

  public List<ResearchProject> getProject1127List()
  { 
    log.info("getProject1127List");
    return project1127List;
  }

  @Factory("project1127List") public void initProject1127List()
  { 
    log.info("initProject1127List");
    project1127List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}