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
    initPerson165List();
    initPerson31List();
    initProject22List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson2(Person person166)
  { 
    user.setPerson(person166);
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

  private String newPerson165;

  public void setNewPerson165(String p)
  { 
    newPerson165 = p;
  }

  public String getNewPerson165()
  { 
    return newPerson165;
  }

  public void selectPerson165(ValueChangeEvent event)
  { 
    log.info("selectPerson165" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person165 = em.find(Person.class, id);
      setPerson2(person165);
    }
  }

  @DataModel("person165List") private Map<String, String> person165List;

  public Map<String, String> getPerson165List()
  { 
    return person165List;
  }

  @Factory("person165List") public void initPerson165List()
  { 
    log.info("initPerson165List");
    person165List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person165List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person31List") private List<Person> person31List;

  public List<Person> getPerson31List()
  { 
    log.info("getPerson31List");
    return person31List;
  }

  @Factory("person31List") public void initPerson31List()
  { 
    log.info("initPerson31List");
    person31List = em.createQuery("from " + "Person").getResultList();
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