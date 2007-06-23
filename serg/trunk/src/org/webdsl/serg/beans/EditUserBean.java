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
    initPerson149List();
    initPerson28List();
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

  public void setPerson1(Person person150)
  { 
    user.setPerson(person150);
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

  private String newPerson149;

  public void setNewPerson149(String p)
  { 
    newPerson149 = p;
  }

  public String getNewPerson149()
  { 
    return newPerson149;
  }

  public void selectPerson149(ValueChangeEvent event)
  { 
    log.info("selectPerson149" + ": new value = " + " " + event.getNewValue());
    Person person149 = (Person)event.getNewValue();
  }

  @DataModel("person149List") private Map<String, String> person149List;

  public Map<String, String> getPerson149List()
  { 
    return person149List;
  }

  @Factory("person149List") public void initPerson149List()
  { 
    log.info("initPerson149List");
    person149List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person149List.put(p.getName(), p.getId().toString());
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