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
    initPerson13List();
    initPerson1011List();
    initProject1111List();
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

  public void setPerson0(Person person14)
  { 
    user.setPerson(person14);
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

  private String newPerson13;

  public void setNewPerson13(String p)
  { 
    newPerson13 = p;
  }

  public String getNewPerson13()
  { 
    return newPerson13;
  }

  public void selectPerson13(ValueChangeEvent event)
  { 
    log.info("selectPerson13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person13 = em.find(Person.class, id);
      setPerson0(person13);
    }
  }

  @DataModel("person13List") private Map<String, String> person13List;

  public Map<String, String> getPerson13List()
  { 
    return person13List;
  }

  @Factory("person13List") public void initPerson13List()
  { 
    log.info("initPerson13List");
    person13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person13List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1011List") private List<Person> person1011List;

  public List<Person> getPerson1011List()
  { 
    log.info("getPerson1011List");
    return person1011List;
  }

  @Factory("person1011List") public void initPerson1011List()
  { 
    log.info("initPerson1011List");
    person1011List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1111List") private List<ResearchProject> project1111List;

  public List<ResearchProject> getProject1111List()
  { 
    log.info("getProject1111List");
    return project1111List;
  }

  @Factory("project1111List") public void initProject1111List()
  { 
    log.info("initProject1111List");
    project1111List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}