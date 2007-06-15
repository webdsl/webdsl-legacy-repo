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
    User var16 = new User();
    user = var16;
    initPerson134List();
    initPerson22List();
    initProject21List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson1(Person person135)
  { 
    user.setPerson(person135);
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

  private String newPerson134;

  public void setNewPerson134(String p)
  { 
    newPerson134 = p;
  }

  public String getNewPerson134()
  { 
    return newPerson134;
  }

  public void selectPerson134(ValueChangeEvent event)
  { 
    log.info("selectPerson134" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person134 = em.find(Person.class, id);
      setPerson1(person134);
    }
  }

  @DataModel("person134List") private Map<String, String> person134List;

  public Map<String, String> getPerson134List()
  { 
    return person134List;
  }

  @Factory("person134List") public void initPerson134List()
  { 
    log.info("initPerson134List");
    person134List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person134List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person22List") private List<Person> person22List;

  public List<Person> getPerson22List()
  { 
    log.info("getPerson22List");
    return person22List;
  }

  @Factory("person22List") public void initPerson22List()
  { 
    log.info("initPerson22List");
    person22List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project21List") private List<ResearchProject> project21List;

  public List<ResearchProject> getProject21List()
  { 
    log.info("getProject21List");
    return project21List;
  }

  @Factory("project21List") public void initProject21List()
  { 
    log.info("initProject21List");
    project21List = em.createQuery("from " + "ResearchProject").getResultList();
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