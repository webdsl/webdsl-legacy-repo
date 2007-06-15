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
    initPerson132List();
    initPerson21List();
    initProject20List();
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

  public void setPerson0(Person person133)
  { 
    user.setPerson(person133);
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

  private String newPerson132;

  public void setNewPerson132(String p)
  { 
    newPerson132 = p;
  }

  public String getNewPerson132()
  { 
    return newPerson132;
  }

  public void selectPerson132(ValueChangeEvent event)
  { 
    log.info("selectPerson132" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person132 = em.find(Person.class, id);
      setPerson0(person132);
    }
  }

  @DataModel("person132List") private Map<String, String> person132List;

  public Map<String, String> getPerson132List()
  { 
    return person132List;
  }

  @Factory("person132List") public void initPerson132List()
  { 
    log.info("initPerson132List");
    person132List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person132List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person21List") private List<Person> person21List;

  public List<Person> getPerson21List()
  { 
    log.info("getPerson21List");
    return person21List;
  }

  @Factory("person21List") public void initPerson21List()
  { 
    log.info("initPerson21List");
    person21List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project20List") private List<ResearchProject> project20List;

  public List<ResearchProject> getProject20List()
  { 
    log.info("getProject20List");
    return project20List;
  }

  @Factory("project20List") public void initProject20List()
  { 
    log.info("initProject20List");
    project20List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}