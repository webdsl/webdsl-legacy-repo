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
    initPerson144List();
    initPerson27List();
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

  public void setPerson1(Person person145)
  { 
    user.setPerson(person145);
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

  private String newPerson144;

  public void setNewPerson144(String p)
  { 
    newPerson144 = p;
  }

  public String getNewPerson144()
  { 
    return newPerson144;
  }

  public void selectPerson144(ValueChangeEvent event)
  { 
    log.info("selectPerson144" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person144 = em.find(Person.class, id);
      setPerson1(person144);
    }
  }

  @DataModel("person144List") private Map<String, String> person144List;

  public Map<String, String> getPerson144List()
  { 
    return person144List;
  }

  @Factory("person144List") public void initPerson144List()
  { 
    log.info("initPerson144List");
    person144List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person144List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person27List") private List<Person> person27List;

  public List<Person> getPerson27List()
  { 
    log.info("getPerson27List");
    return person27List;
  }

  @Factory("person27List") public void initPerson27List()
  { 
    log.info("initPerson27List");
    person27List = em.createQuery("from " + "Person").getResultList();
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