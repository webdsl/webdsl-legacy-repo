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

@Stateful @Name("createUser") public class CreateUserBean  implements CreateUserBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createUser" + ".initalize()");
    User var18 = new User();
    user = var18;
    initPerson135List();
    initPerson28List();
    initProject22List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson2(Person person136)
  { 
    user.setPerson(person136);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getUser());
    return "/" + "viewUser" + ".seam?" + ("user" + "=" + user.getId() + "");
  }

  private String newPerson135;

  public void setNewPerson135(String p)
  { 
    newPerson135 = p;
  }

  public String getNewPerson135()
  { 
    return newPerson135;
  }

  public void selectPerson135(ValueChangeEvent event)
  { 
    log.info("selectPerson135" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person135 = em.find(Person.class, id);
      setPerson2(person135);
    }
  }

  @DataModel("person135List") private Map<String, String> person135List;

  public Map<String, String> getPerson135List()
  { 
    return person135List;
  }

  @Factory("person135List") public void initPerson135List()
  { 
    log.info("initPerson135List");
    person135List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person135List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person28List") private List<Person> person28List;

  public List<Person> getPerson28List()
  { 
    log.info("getPerson28List");
    return person28List;
  }

  @Factory("person28List") public void initPerson28List()
  { 
    log.info("initPerson28List");
    person28List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project22List") private List<ResearchProject> project22List;

  public List<ResearchProject> getProject22List()
  { 
    log.info("getProject22List");
    return project22List;
  }

  @Factory("project22List") public void initProject22List()
  { 
    log.info("initProject22List");
    project22List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private User user;

  public User getUser()
  { 
    log.info("getUser");
    return user;
  }

  public void setUser(User user)
  { 
    log.info("setUser");
    this.user = user;
  }
}