package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
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
    User var30 = new User();
    user = var30;
    initPerson0List();
    initPerson10List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson0(Person person1)
  { 
    user.setPerson(person1);
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

  private String newPerson0;

  public void setNewPerson0(String p)
  { 
    newPerson0 = p;
  }

  public String getNewPerson0()
  { 
    return newPerson0;
  }

  public String selectPerson0()
  { 
    log.info("selectPerson0" + " " + newPerson0);
    Person person0 = em.find(Person.class, new Long(newPerson0));
    setPerson0(person0);
    return null;
  }

  @DataModel("person0List") private Map<String, String> person0List;

  public Map<String, String> getPerson0List()
  { 
    return person0List;
  }

  @Factory("person0List") public void initPerson0List()
  { 
    log.info("initPerson0List");
    person0List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person0List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person10List") private List<Person> person10List;

  public List<Person> getPerson10List()
  { 
    log.info("getPerson10List");
    return person10List;
  }

  @Factory("person10List") public void initPerson10List()
  { 
    log.info("initPerson10List");
    person10List = em.createQuery("from " + "Person").getResultList();
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