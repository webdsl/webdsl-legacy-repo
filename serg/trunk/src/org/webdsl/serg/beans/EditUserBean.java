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

@Stateful @Name("editUser") public class EditUserBean  implements EditUserBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editUser" + ".initalize()");
    if(userId == null)
    { 
      log.info("No " + "userId" + " defined, creating new " + "User");
      user = new User();
    }
    else
    { 
      user = em.find(User.class, userId);
    }
    initPerson128List();
    initPerson22List();
    initProject21List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("user") private Long userId;

  private User user;

  public void setUser(User user)
  { 
    log.info("setUser");
    this.user = user;
  }

  public User getUser()
  { 
    log.info("getUser");
    return user;
  }

  public void setPerson1(Person person129)
  { 
    user.setPerson(person129);
  }

  @End public String cancel()
  { 
    return "/" + "viewUser" + ".seam?" + ("user" + "=" + user.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getUser());
    return "/" + "viewUser" + ".seam?" + ("user" + "=" + user.getId() + "");
  }

  private String newPerson128;

  public void setNewPerson128(String p)
  { 
    newPerson128 = p;
  }

  public String getNewPerson128()
  { 
    return newPerson128;
  }

  public void selectPerson128(ValueChangeEvent event)
  { 
    log.info("selectPerson128" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person128 = em.find(Person.class, id);
      setPerson1(person128);
    }
  }

  @DataModel("person128List") private Map<String, String> person128List;

  public Map<String, String> getPerson128List()
  { 
    return person128List;
  }

  @Factory("person128List") public void initPerson128List()
  { 
    log.info("initPerson128List");
    person128List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person128List.put(p.getName(), p.getId().toString());
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
}