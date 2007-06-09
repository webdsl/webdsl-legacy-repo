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

@Stateful @Name("createPresentation") public class CreatePresentationBean  implements CreatePresentationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createPresentation" + ".initalize()");
    Presentation var24 = new Presentation();
    presentation = var24;
    initPerson32List();
    initPerson1031List();
    initProject1131List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson13(Person person33)
  { 
    presentation.setSpeaker(person33);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getPresentation());
    return "/" + "viewPresentation" + ".seam?" + ("presentation" + "=" + presentation.getId() + "");
  }

  private String newPerson32;

  public void setNewPerson32(String p)
  { 
    newPerson32 = p;
  }

  public String getNewPerson32()
  { 
    return newPerson32;
  }

  public void selectPerson32(ValueChangeEvent event)
  { 
    log.info("selectPerson32" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person32 = em.find(Person.class, id);
      setPerson13(person32);
    }
  }

  @DataModel("person32List") private Map<String, String> person32List;

  public Map<String, String> getPerson32List()
  { 
    return person32List;
  }

  @Factory("person32List") public void initPerson32List()
  { 
    log.info("initPerson32List");
    person32List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person32List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1031List") private List<Person> person1031List;

  public List<Person> getPerson1031List()
  { 
    log.info("getPerson1031List");
    return person1031List;
  }

  @Factory("person1031List") public void initPerson1031List()
  { 
    log.info("initPerson1031List");
    person1031List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1131List") private List<ResearchProject> project1131List;

  public List<ResearchProject> getProject1131List()
  { 
    log.info("getProject1131List");
    return project1131List;
  }

  @Factory("project1131List") public void initProject1131List()
  { 
    log.info("initProject1131List");
    project1131List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Presentation presentation;

  public Presentation getPresentation()
  { 
    log.info("getPresentation");
    return presentation;
  }

  public void setPresentation(Presentation presentation)
  { 
    log.info("setPresentation");
    this.presentation = presentation;
  }
}