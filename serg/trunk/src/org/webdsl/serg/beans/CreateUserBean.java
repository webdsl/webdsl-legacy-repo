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
    User var22 = new User();
    user = var22;
    initPerson151List();
    initPerson29List();
    initProject22List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson2(Person person152)
  { 
    user.setPerson(person152);
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

  private String newPerson151;

  public void setNewPerson151(String p)
  { 
    newPerson151 = p;
  }

  public String getNewPerson151()
  { 
    return newPerson151;
  }

  public void selectPerson151(ValueChangeEvent event)
  { 
    log.info("selectPerson151" + ": new value = " + " " + event.getNewValue());
    Person person151 = (Person)event.getNewValue();
  }

  @DataModel("person151List") private Map<String, String> person151List;

  public Map<String, String> getPerson151List()
  { 
    return person151List;
  }

  @Factory("person151List") public void initPerson151List()
  { 
    log.info("initPerson151List");
    person151List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person151List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person29List") private List<Person> person29List;

  public List<Person> getPerson29List()
  { 
    log.info("getPerson29List");
    return person29List;
  }

  @Factory("person29List") public void initPerson29List()
  { 
    log.info("initPerson29List");
    person29List = em.createQuery("from " + "Person").getResultList();
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